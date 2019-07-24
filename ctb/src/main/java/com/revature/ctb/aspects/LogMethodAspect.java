package com.revature.ctb.aspects;

import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.revature.ctb.domains.Employee;
import com.revature.ctb.restcontrollers.BasedRestController;
import com.revature.ctb.utils.LogUtil;

@Aspect
@Component
public class LogMethodAspect {

	private HttpSession session;

	@Autowired
	public void setSession(HttpSession session) {
		this.session = session;
	}

	/**
	 * Defining Pointcut to be executed every time a dao's method gets called
	 */
	@Pointcut("execution(* com.revature.ctb.daos.*.*(..))")
	public void logDaoMethodPointcut() {

	}

	/**
	 * Defining Pointcut to be executed every time a service's method gets called
	 */
	@Pointcut("execution(* com.revature.ctb.services.*.*(..))")
	public void logServiceMethodPointcut() {

	}

	/**
	 * Defining Pointcut to be executed every time a service's method gets called
	 */
	@Pointcut("execution(* com.revature.ctb.restcontrollers.*.*(..))")
	public void logRestControllerMethodPointcut() {

	}

	/**
	 * Implementation of login for methods in the doao, service and web layer.
	 * 
	 * @param theJoinPoint Provides access to the method that was intercepted
	 * @throws Throwable
	 */
	@Before("logDaoMethodPointcut() || logServiceMethodPointcut() || logRestControllerMethodPointcut()")
	public void logDebugBefore(JoinPoint theJointPoint) throws Throwable {
		LogUtil.debug("EXECUTING: " + theJointPoint.getSignature().toShortString());

		if (session != null) {
			Employee employee = (Employee) session.getAttribute(BasedRestController.EMPLOYEE_SESSION_KEY);

			if (employee != null) {
				LogUtil.debug(">>>>>>>> Employee: " + employee.toString());
				LogUtil.debug(">>>>>>>> Roles: " + employee.getRoles().toString());
			} else {
				LogUtil.debug(">>>>>>>> Not logged in");
			}
		}
	}

}
