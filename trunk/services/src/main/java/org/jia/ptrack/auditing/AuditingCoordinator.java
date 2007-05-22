package org.jia.ptrack.auditing;

import java.util.Set;

import org.jia.ptrack.domain.EntityIdAndClass;

public interface AuditingCoordinator {

	void recordCall(String methodName, Set<EntityIdAndClass> entities);

}
