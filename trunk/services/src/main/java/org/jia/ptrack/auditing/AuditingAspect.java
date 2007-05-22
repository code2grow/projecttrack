package org.jia.ptrack.auditing;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.jia.ptrack.domain.EntityIdAndClass;
import org.springframework.core.Ordered;

@Aspect
public class AuditingAspect implements Ordered {

	private Log logger = LogFactory.getLog(getClass());

	private int order;

	private AuditingCoordinator auditingCoordinator;

	private final Class[] entityClasses;
	
	public AuditingAspect(AuditingCoordinator auditingCoordinator, Class[] entityClasses) {
		this.auditingCoordinator = auditingCoordinator;
		this.entityClasses = entityClasses;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}
	
	@Around("org.jia.ptrack.architecture.SystemArchitecture.serviceMethod()")
	public Object auditLog(ProceedingJoinPoint jp) throws Throwable {
		logger.debug("entering auditLog");
		Object result = jp.proceed();
		auditingCoordinator.recordCall(jp.getStaticPart().getSignature().getName(), getEntities(jp.getArgs(), result));
		logger.debug("leaving auditLog");
		return result;
	}

	private Set<EntityIdAndClass> getEntities(Object[] args, Object result) {
		Set<EntityIdAndClass> entities = new HashSet<EntityIdAndClass>();
		for (Object arg: args) {
			if (isEntity(arg))
				entities.add(makeEntityId(arg));
			
		}
		if (result != null) {
			if (Collection.class.isAssignableFrom(result.getClass())) {
				for (Object resultObject : (Collection)result) {
					if (isEntity(resultObject))
						entities.add(makeEntityId(resultObject));
							
				}
				
			} else {
				if (isEntity(result))
					entities.add(makeEntityId(result));
				
			}
		}
		return entities;
	}

	private EntityIdAndClass makeEntityId(Object result) {
		return new EntityIdAndClass(result);
	}

	private boolean isEntity(Object result) {
		for (Class type : entityClasses) {
			if (type.isInstance(result))
				return true;
		}
		return false;
	}

}
