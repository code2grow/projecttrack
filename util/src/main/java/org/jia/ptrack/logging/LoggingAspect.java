package org.jia.ptrack.logging;

import net.chrisrichardson.arid.logging.AbstractLoggingAspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect("pertarget(org.jia.ptrack.logging.LoggingAspect.loggableOperation())")
public class LoggingAspect extends AbstractLoggingAspect {

	@Override
	@Pointcut("org.jia.ptrack.architecture.SystemArchitecture.componentMethod()")
	public
	void loggableOperation() {
	}
}
