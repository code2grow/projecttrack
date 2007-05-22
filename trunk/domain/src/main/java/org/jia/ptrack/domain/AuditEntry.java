package org.jia.ptrack.domain;

import java.util.Date;
import java.util.Set;

public class AuditEntry {

	private int id;

	private String username;

	private Date time;

	private String methodName;

	private Set<EntityIdAndClass> entities;

	AuditEntry() {
	}

	public AuditEntry(String username, Date date, String methodName,
			Set<EntityIdAndClass> entities) {
		this.username = username;
		this.time = date;
		this.methodName = methodName;
		this.entities = entities;
	}

	public int getId() {
		return id;
	}

	public Date getTime() {
		return time;
	}

	public String getMethodName() {
		return methodName;
	}

	public String getUsername() {
		return username;
	}

	public Set<EntityIdAndClass> getEntities() {
		return entities;
	}
}
