package org.jia.ptrack.jmx;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

public class MethodCallRecorder {

	private ConcurrentMap<String, AtomicLong> map = new ConcurrentHashMap<String, AtomicLong>();

	public void recordCall(Class declaringType, String name) {
		AtomicLong atomicLong = new AtomicLong(0);
		AtomicLong oldEntry = map.putIfAbsent(name, atomicLong);
		if (oldEntry != null)
			oldEntry.incrementAndGet();
		else
			atomicLong.incrementAndGet();
	}

	public Map<String, Long> getCallCounts() {
		Map<String, Long> result = new HashMap<String, Long>();
		for (Map.Entry<String, AtomicLong> entry : map.entrySet()) {
			result.put(entry.getKey(), entry.getValue().longValue());
		}
		return result;
	}

	public long getCallCount(String name) {
		AtomicLong al = map.get(name);
		return al == null ? 0 : al.longValue();
	}

}
