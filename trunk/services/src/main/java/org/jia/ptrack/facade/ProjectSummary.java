package org.jia.ptrack.facade;

import org.jia.ptrack.domain.ProjectType;

public class ProjectSummary {
	
	
	private int id;
	private String name;
	private ProjectType type;
	private String statusName;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public ProjectType getType() {
		return type;
	}
	public void setType(ProjectType type) {
		this.type = type;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
}
