package org.jia.ptrack.ws.springws.client;

import org.mortbay.jetty.Server;
import org.mortbay.jetty.bio.SocketConnector;
import org.mortbay.jetty.webapp.WebAppContext;

public class ServerLauncher {

  private Server server;

  public ServerLauncher() {
    server = new Server();
    SocketConnector connector = new SocketConnector();
    connector.setPort(8080);
    server.addConnector(connector);
    WebAppContext context = new WebAppContext("src/main/webapp", "/webapp");
    server.addHandler(context);
   }
  
  public void start() throws Exception {
    server.start();
  }
  
  public void stop() throws Exception {
    server.stop();
  }
  
  public static void main(String[] args) throws Exception {
    ServerLauncher server = new ServerLauncher();
    server.start();
  }
  
}
