package org.jia.ptrack.auditing;

import java.util.Date;
import java.util.Set;

import org.jia.ptrack.domain.AuditEntry;
import org.jia.ptrack.domain.AuditEntryRepository;
import org.jia.ptrack.domain.EntityIdAndClass;
import org.jia.ptrack.services.SecurityInfoProvider;

public class AuditingCoordinatorImpl implements AuditingCoordinator {

	private AuditEntryRepository auditRepository;

	private SecurityInfoProvider securityInfoProvider;

	public AuditingCoordinatorImpl(AuditEntryRepository auditRepository,
			SecurityInfoProvider securityInfoProvider) {
		this.auditRepository = auditRepository;
		this.securityInfoProvider = securityInfoProvider;
	}

	public void recordCall(String methodName, Set<EntityIdAndClass> entities) {
		auditRepository.add(new AuditEntry(securityInfoProvider.getUsername(),
				new Date(), methodName, entities));
	}

}
