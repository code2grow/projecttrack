package org.jia.ptrack.ws.cxf.server;

public class GetProject {

  private String id;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  int getIdAsInt() {
    return Integer.parseInt(getId());
  }
  
}
