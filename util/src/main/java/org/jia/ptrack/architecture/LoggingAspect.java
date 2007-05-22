package org.jia.ptrack.architecture;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LoggingAspect {

	@Pointcut("org.jia.ptrack.architecture.SystemArchitecture.serviceMethod() || org.jia.ptrack.architecture.SystemArchitecture.daoMethod()")
	void loggableOperation() {
	}

	@Around("loggableOperation()")
	public Object logIt(ProceedingJoinPoint jp) throws Throwable {
		Log logger = LogFactory.getLog(jp.getTarget().getClass());
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
