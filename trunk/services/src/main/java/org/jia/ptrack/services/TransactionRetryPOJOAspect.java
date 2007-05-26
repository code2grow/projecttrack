package org.jia.ptrack.services;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.Ordered;
import org.springframework.dao.ConcurrencyFailureException;

@Aspect
public class TransactionRetryPOJOAspect implements Ordered {

	private Log logger = LogFactory.getLog(getClass());

	protected int maxRetries = 3;

	private int order;

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public void setMaxRetries(int maxRetries) {
		this.maxRetries = maxRetries;
	}

	
	@Pointcut("org.jia.ptrack.architecture.SystemArchitecture.serviceMethod()")
	void retriableOperation() {
	}

	@Around("retriableOperation()")
	public Object retryTransaction(ProceedingJoinPoint jp) throws Throwable {
		// pia-lab-method-stub(spring-transactions)
		logger.debug("entering retryTransaction");
		int retries = 0;
		while (true)
			try {
				Object result = jp.proceed();
				logger.debug("leaving retryTransaction");
				return result;
			} catch (ConcurrencyFailureException e) {
				if (retries++ > maxRetries)
					throw e;
				else {
					logger.debug("retrying");
					continue;
				}
			}

	}

}
