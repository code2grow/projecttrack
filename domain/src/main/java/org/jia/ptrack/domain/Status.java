/**
 * 
 */
package org.jia.ptrack.domain;

import java.io.Serializable;

public class Status implements Serializable {
	private int id;

	private String name;

	private Status rejectionStatus;

	private Status approvalStatus;

	private boolean initialState;

	private boolean finalState;

	RoleType role;

	public Status() {
		// For Hibernate
	}
	
	Status(String name, RoleType role) {
		this.name = name;
		this.initialState = false;
		this.finalState = false;
		this.role = role;
	}


	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	public RoleType getRole() {
		return role;
	}

	void setApprovalStatus(Status approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public Status getApprovalStatus() {
		return approvalStatus;
	}

	void setInitialState(boolean initialState) {
		this.initialState = initialState;
	}

	public boolean isInitialState() {
		return initialState;
	}

	public void setFinalState(boolean finalState) {
		this.finalState = finalState;
	}

	public boolean isFinalState() {
		return finalState;
	}

	public void setRejectionStatus(Status rejectionStatus) {
		this.rejectionStatus = rejectionStatus;
	}

	public Status getRejectionStatus() {
		return rejectionStatus;
	}

	public String toString() {
		return name;
	}

	public boolean isValidStateChange(boolean approve) {
		if (approve) {
			return !isFinalState();
		} else {
			return (!isInitialState() && !isFinalState());
		}
	}


	boolean isValidStateChange(boolean approve, RoleType userRole) {
		return !isValidStateChange(approve) || !role.equals(userRole);
	}

	
}