package org.jia.ptrack.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

public class Password implements Serializable {

  private String passwordString;

  Password() {
  }

  public Password(String passwordString) {
    this.passwordString = passwordString.trim();
  }

  public String getPasswordString() {
    return passwordString;
  }

  public String toString() {
    return new ToStringBuilder(this).append("passwordString", "*****")
        .toString();
  }

}
