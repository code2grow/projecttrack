/**
 * 
 */
package org.jia.ptrack.domain;

public class Status  {
	int id;

	String name;

	Status rejectionStatus;

	Status approvalStatus;

	boolean initialState;

	boolean finalState;

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


	public String getName() {
		return name;
	}

	public RoleType getRole() {
		return role;
	}

	public Status getApprovalStatus() {
		return approvalStatus;
	}

	public Status getRejectionStatus() {
		return rejectionStatus;
	}

	public boolean isFinalState() {
		return finalState;
	}

	public boolean isInitialState() {
		return initialState;
	}

	public String toString() {
		return name;
	}

// Don't think we need this
//	public boolean equals(Object other) {
//		return ((Status) other).getId().equals(id);
//	}

	public boolean isValidStateChange(boolean approve) {
		if (approve) {
			return !isFinalState();
		} else {
			return (!isInitialState() && !isFinalState());
		}
	}


	public String getId() {
		return Integer.toString(id); // FIXME
	}

}