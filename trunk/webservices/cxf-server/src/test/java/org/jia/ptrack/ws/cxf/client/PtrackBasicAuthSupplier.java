package org.jia.ptrack.ws.cxf.client;

import java.net.URL;

import org.apache.cxf.message.Message;
import org.apache.cxf.transport.http.HttpBasicAuthSupplier;

public class PtrackBasicAuthSupplier extends HttpBasicAuthSupplier {

  @Override
  public UserPass getPreemptiveUserPass(String conduitName, URL currentURL,
      Message message) {
    return createUserPass("proj_mgr", "faces");
  }

  @Override
  public UserPass getUserPassForRealm(String conduitName, URL currentURL,
      Message message, String realm) {
    return createUserPass("proj_mgr", "faces");
  }

}
