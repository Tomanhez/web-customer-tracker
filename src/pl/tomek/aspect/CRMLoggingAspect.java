package pl.tomek.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CRMLoggingAspect {

	private Logger logger = Logger.getLogger(getClass().getName());

	@Pointcut("execution(* pl.tomek.controller.*.*(..))") // controller.*(any class).*(any method)(..)(any argument in
															// method)
	private void forControllerPackage() {
	}

	@Pointcut("execution(* pl.tomek.service.*.*(..))")
	private void forServicePackage() {
	}

	@Pointcut("execution(* pl.tomek.dao.*.*(..))")
	private void forDAOPackage() {
	}

	@Pointcut("forControllerPackage() || forServicePackage() || forDAOPackage()")
	private void forAppFlow() {
	}

	@Before("forAppFlow()")
	public void before(JoinPoint joinPoint) {
		
		String method = joinPoint.getSignature().toShortString();
		logger.info("====>> in @Before: calling method: " + method);
		
		Object[] args = joinPoint.getArgs();

		for (Object tempArg : args) {
			logger.info("====>> argument: " + tempArg);
		}
	}

	@AfterReturning(pointcut = "forAppFlow()", returning = "result")
	public void afterReturning(JoinPoint joinPoint, Object result) {

		String method = joinPoint.getSignature().toShortString();
		logger.info("====>> in @AfterReturning: from method: " + method);

		logger.info("====>> result: " + result);
	}

}
