package org.jia.ptrack.jmx;

public class CallRecord {
	private long count;

	private long failedCount;

	private long durationInMilliseconds;

	public synchronized void recordSuccess(long durationInMilliseconds) {
	  count++;
		this.durationInMilliseconds += durationInMilliseconds;
	}

	public synchronized long getCount() {
		return count;
	}

	public synchronized long getFailedCount() {
		return failedCount;
	}

	public synchronized double getAverageDurationInMilliSeconds() {
		return count == 0 ? 0 : durationInMilliseconds / (double) count;
	}

	public void recordFailure(long duration) {
		count++;
		failedCount++;
		this.durationInMilliseconds += durationInMilliseconds;
	}

}
