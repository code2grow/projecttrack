package org.jia.ptrack.domain;

import java.io.Serializable;

public class PersonName implements Serializable {
  private String firstName;
  private String lastName;

  PersonName() {
  }
  
  public PersonName(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
}