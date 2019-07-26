package com.revature.ctb.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.revature.ctb.utils.LogUtil;

@Aspect
@Component
public class TransactionAspect {

	private SessionFactory sf;
	private Session session;
	private Transaction transaction;
	private String methodSignature = null;

	@Autowired
	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}

	/**
	 * Defining Pointcut to be executed every time a service's method gets called
	 */
	@Pointcut("execution(* com.revature.ctb.services.*.*(..))")
	public void handleTransactionsPointcut() {

	}

	/**
	 * Implementation of the Transaction aspect. This aspect creates only one
	 * transaction per service method so that the transaction only gets commited
	 * once the method that started the transaction is done.
	 * 
	 * @param theProceedingJoinPoint Provides access to the method that was
	 *                               intercepted
	 * @return
	 * @throws Throwable
	 */
	@Around("handleTransactionsPointcut()")
	public Object aroundServiceMethod(ProceedingJoinPoint theProceedingJoinPoint) throws Throwable {
		// saving the name of the method that start the transaction
		if (methodSignature == null) {
			methodSignature = theProceedingJoinPoint.getSignature().toShortString();
		}

		try {
			LogUtil.debug(">>>>>>>>> TransactionAspect - trying to get the session");

			session = sf.getCurrentSession(); // Trying to obtain the session
			
		} catch (Exception e) {
			LogUtil.debug(">>>>>>>>> TransactionAspect - opening the session");
			
			session = sf.openSession(); // Open a new session if this is not opened
		}

		// Check if a transaction is not running or not active, start a new one
		if (transaction == null || !transaction.isActive()) {
			LogUtil.debug(">>>>>>>>> TransactionAspect - starting the transaction");
			LogUtil.debug(">>>>>>>>> Session - " + session.isOpen());

			transaction = session.beginTransaction();
		}

		// now, let's execute the method
		Object result = theProceedingJoinPoint.proceed();

		session.flush();

		// Verifying if the method that is currently being called is the same that
		// started the transaction, if so we can commit the transactions fired
		// by this method
		if (transaction != null && transaction.isActive()
				&& theProceedingJoinPoint.getSignature().toShortString().equals(methodSignature)) {

			LogUtil.debug(">>>>>>>>> TransactionAspect - commiting transaction");

			transaction.commit();
			transaction = null;
			session = null;

			methodSignature = null;
		}

		return result;
	}

	@AfterThrowing(pointcut = "handleTransactionsPointcut()", throwing = "theExc")
	public void afterThrowingRollbackTransaction(JoinPoint theJoinPoint, Throwable theExc) {

		if (session != null && transaction.isActive()) {
			LogUtil.debug(">>>>> TransactionAspect - rolling back transaction");

			transaction.rollback();
			transaction = null;
			session = null;
			
			methodSignature = null;
		}
	}

}
