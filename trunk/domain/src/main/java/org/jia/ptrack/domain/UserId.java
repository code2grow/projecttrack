package org.jia.ptrack.domain;

import java.io.Serializable;

public class UserId implements Serializable {
  private String login;

  UserId() {
  }
  
  public UserId(String login) {
    this.login = login == null ? null : login.trim().toLowerCase();
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }
}