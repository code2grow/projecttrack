package org.jia.ptrack.facade;

import org.jia.ptrack.domain.ArtifactType;

public class ProjectDetails extends ProjectSummary {

	private String initiatedBy;
	private String requirementsContactName;
	private String requirementsContactEmail;
	private String initialComments;
	private ArtifactType[] artifacts;
	private boolean approvable;
	private boolean rejectable;
	private int version;
	
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public boolean isApprovable() {
		return approvable;
	}
	public void setApprovable(boolean acceptable) {
		this.approvable = acceptable;
	}
	public boolean isRejectable() {
		return rejectable;
	}
	public void setRejectable(boolean rejectable) {
		this.rejectable = rejectable;
	}
	public String getInitiatedBy() {
		return initiatedBy;
	}
	public void setInitiatedBy(String initiatedBy) {
		this.initiatedBy = initiatedBy;
	}
	public String getRequirementsContactName() {
		return requirementsContactName;
	}
	public void setRequirementsContactName(String requirementsContactName) {
		this.requirementsContactName = requirementsContactName;
	}
	public String getRequirementsContactEmail() {
		return requirementsContactEmail;
	}
	public void setRequirementsContactEmail(String requirementsContactEmail) {
		this.requirementsContactEmail = requirementsContactEmail;
	}
	public String getInitialComments() {
		return initialComments;
	}
	public void setInitialComments(String initialComments) {
		this.initialComments = initialComments;
	}
	public ArtifactType[] getArtifacts() {
		return artifacts;
	}
	public void setArtifacts(ArtifactType[] artifacts) {
		this.artifacts = artifacts;
	}
	
	

}
