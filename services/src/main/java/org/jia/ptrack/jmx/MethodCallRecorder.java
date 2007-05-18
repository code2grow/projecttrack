package org.jia.ptrack.jmx;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

public class MethodCallRecorder {

	private ConcurrentMap<Class, ConcurrentMap<String, AtomicLong>> map = new ConcurrentHashMap<Class, ConcurrentMap<String, AtomicLong>>();

	public void recordCall(Class declaringType, String name) {
		ConcurrentMap<String, AtomicLong> mapForType = map.get(declaringType);
		if (mapForType == null) {
			mapForType = new ConcurrentHashMap<String, AtomicLong>();
			ConcurrentMap<String, AtomicLong> existingEntry = map.putIfAbsent(
					declaringType, mapForType);
			if (existingEntry != null)
				mapForType = existingEntry;
		}
		AtomicLong count = mapForType.get(name);
		if (count == null) {
			count = new AtomicLong(0);
			AtomicLong oldEntry = mapForType.putIfAbsent(name, count);
			if (oldEntry != null)
				count = oldEntry;

		}
		count.incrementAndGet();
	}

	public long getCallCount(Class type, String name) {
		ConcurrentMap<String, AtomicLong> mapForType = map.get(type);
		if (mapForType == null)
			return 0;
		AtomicLong al = mapForType.get(name);
		return al == null ? 0 : al.longValue();
	}

}
