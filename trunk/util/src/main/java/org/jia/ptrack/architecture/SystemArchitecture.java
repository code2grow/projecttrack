package org.jia.ptrack.architecture;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class SystemArchitecture {

	@Pointcut("execution(public * org.jia.ptrack.services.*Coordinator.*(..))")
	public void serviceMethod() {}

	@Pointcut("execution(public * org.jia.ptrack.domain.*Repository.*(..))")
	public void daoMethod() {}
}
