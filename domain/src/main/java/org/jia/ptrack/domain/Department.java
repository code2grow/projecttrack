package org.jia.ptrack.domain;

import java.io.Serializable;

public class Department implements Serializable {

	private int id;
	private String name;

	public Department() {
	}
	
	public Department(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
	