package org.jia.ptrack.domain;


public class StateMachine {

	private int id;

	private Status initialStatus;

	private String name;

	StateMachine() {
	}
	
	public StateMachine(String name, Status initialStatus) {
		this.name = name;
		this.initialStatus = initialStatus;
	}

	public Status getInitialStatus() {
		return initialStatus;
	}

	public int getId() {
		return id;
	}

}
