package org.jia.ptrack.jmx;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;

public class MethodCountingAspect {

	private MethodCallRecorder recorder = new MethodCallRecorder();

	public Object recordMethodInvocation(ProceedingJoinPoint jp)
			throws Throwable {
		Signature signature = jp.getStaticPart().getSignature();
		long startTime = System.currentTimeMillis();
		try {
			Object result = jp.proceed();
			long endTime = System.currentTimeMillis();
			recorder.recordSuccessfulCall(signature, endTime - startTime);
			return result;
		} catch (Throwable t) {
			long endTime = System.currentTimeMillis();
			recorder.recordFailedCall(signature, endTime - startTime, t);
			throw t;
		}
	}

	public long getCallCount(Class type, String name) {
	  return recorder.getCallCount(type, name);
	}

	public Object getFailedCallCount(Class type, String name) {
		return recorder.getFailedCallCount(type, name);
	}

	public Object getAverageTime(Class type, String name) {
		return recorder.getAverageTime(type, name);
	}
}
