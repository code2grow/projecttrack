package org.jia.ptrack.domain;

import java.io.Serializable;

public class Contact implements Serializable {
	private String name;
	private String email;

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
