package org.jia.ptrack.architecture;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect("pertarget(org.jia.ptrack.architecture.SystemArchitecture.componentMethod())")
public class LoggingAspect {

	private Log logger;

	@Pointcut("org.jia.ptrack.architecture.SystemArchitecture.componentMethod()")
	void loggableOperation() {
	}

	@Around("loggableOperation()")
	public Object logIt(ProceedingJoinPoint jp) throws Throwable {
		// FIXME - synchronization issue???
		if (logger == null) {
			Class<? extends Object> targetClass = jp.getTarget().getClass();
			logger = LogFactory.getLog(targetClass);
		}
		if (logger.isDebugEnabled()) {
			Signature signature = jp.getStaticPart().getSignature();
			logger.debug("entering: " + signature.getName());
			try {
				Object result = jp.proceed();
				return result;
			} catch (Throwable e) {
				logger.debug("exception in: " + signature.getName(), e);
				throw e;
			}
		} else {
			return jp.proceed();
		}
	}
}
