package org.jia.ptrack.jmx;

import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;

public class MethodCountingAspect {

	private MethodCallRecorder recorder = new MethodCallRecorder();

	public Object recordMethodInvocation(ProceedingJoinPoint jp)
			throws Throwable {
		Signature signature = jp.getStaticPart().getSignature();
		recorder
				.recordCall(signature
				.getDeclaringType(), signature
				.getName());
		return jp.proceed();
	}

	public Map<String, Long> getCallCounts() {
		return recorder.getCallCounts();
	}

	public long getCallCount(String name) {
		return recorder.getCallCount(name);
	}
}
