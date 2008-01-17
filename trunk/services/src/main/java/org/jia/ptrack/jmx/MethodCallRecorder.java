package org.jia.ptrack.jmx;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.aspectj.lang.Signature;

public class MethodCallRecorder {

	private ConcurrentMap<Class, ConcurrentMap<String, CallRecord>> map = new ConcurrentHashMap<Class, ConcurrentMap<String, CallRecord>>();

	public void recordSuccessfulCall(Signature signature, long duration) {
		CallRecord count = getOrCreateCallRecord(signature);
		count.recordSuccess(duration);
	}

	private CallRecord getOrCreateCallRecord(Signature signature) {
		Class declaringType = signature.getDeclaringType();
		String name = signature.getName();

		ConcurrentMap<String, CallRecord> mapForType = map.get(declaringType);
		if (mapForType == null) {
			mapForType = new ConcurrentHashMap<String, CallRecord>();
			ConcurrentMap<String, CallRecord> existingEntry = map.putIfAbsent(
					declaringType, mapForType);
			if (existingEntry != null)
				mapForType = existingEntry;
		}
		CallRecord count = mapForType.get(name);
		if (count == null) {
			count = new CallRecord();
			CallRecord oldEntry = mapForType.putIfAbsent(name, count);
			if (oldEntry != null)
				count = oldEntry;

		}
		return count;
	}

	private CallRecord getCallRecord(Class type, String name) {
		ConcurrentMap<String, CallRecord> mapForType = map.get(type);
		if (mapForType == null)
			return null;
		return mapForType.get(name);

	}

	public void recordFailedCall(Signature signature, long duration, Throwable t) {
		CallRecord count = getOrCreateCallRecord(signature);
		count.recordFailure(duration);
	}

	public long getCallCount(Class type, String name) {
	  CallRecord callRecord = getCallRecord(type, name);
		return callRecord == null ? 0 : callRecord.getCount();
	}

	public long getFailedCallCount(Class type, String name) {
		CallRecord callRecord = getCallRecord(type, name);
		return callRecord == null ? 0 : callRecord.getFailedCount();
	}

	public double getAverageTime(Class type, String name) {
		CallRecord callRecord = getCallRecord(type, name);
		return callRecord == null ? 0 : callRecord.getAverageDurationInMilliSeconds();
	}
}
