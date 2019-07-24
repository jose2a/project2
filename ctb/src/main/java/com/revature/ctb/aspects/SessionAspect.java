package com.revature.ctb.aspects;

import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.revature.ctb.domains.Employee;
import com.revature.ctb.exceptions.NotAuthenticatedException;
import com.revature.ctb.restcontrollers.BasedRestController;
import com.revature.ctb.utils.LogUtil;

@Aspect
@Component
public class SessionAspect {

	private HttpSession session;

	@Autowired
	public void setSession(HttpSession session) {
		this.session = session;
	}

	/**
	 * Defining Pointcut to be executed every time a service's method gets called
	 */
	@Pointcut("execution(* com.revature.ctb.restcontrollers.*.*(..))")
	public void sessionRestControllerMethodPointcut() {

	}
	
	@Pointcut("execution(* com.revature.ctb.restcontrollers.EmployeeRestController.login(..))")
	private void login() {}
	
	@Pointcut("execution(* com.revature.ctb.restcontrollers.EmployeeRestController.postEmployee(..))")
	private void register() {}

	/**
	 * Implementation of login for methods in the doao, service and web layer.
	 * 
	 * @param theJoinPoint Provides access to the method that was intercepted
	 * @throws Throwable
	 */
	// TODO: Remove ! form sessionREs
//	@Before("sessionRestControllerMethodPointcut() && !(login() || register())")
	public void sessionBefore(JoinPoint theJointPoint) throws Throwable {
		LogUtil.debug("VERIFYING IF USER LOGGED IN: " + theJointPoint.getSignature().toShortString());

		if (session != null) {
			Employee employee = (Employee) session.getAttribute(BasedRestController.EMPLOYEE_SESSION_KEY);

			if (employee == null) {
				LogUtil.debug(">>>>>>>> Employee not logged in");

				throw new NotAuthenticatedException("Employee not authenticated.");
			}
		}
	}

}
