package org.jia.ptrack.ws.cxf.client;

import java.io.IOException;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import junit.framework.TestCase;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.GetMethod;
import org.xml.sax.InputSource;

public class RestfulCxfClientTests extends TestCase {

	private ServerLauncher server;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		server = new ServerLauncher();
		server.start();
	}

	@Override
	protected void tearDown() throws Exception {
		server.stop();
		super.tearDown();
	}

	
	public void testGetProjectsWaitingForApproval() throws HttpException, IOException, XPathExpressionException {
		HttpClient httpClient = new HttpClient();
		
		httpClient.getState().setCredentials(
	            new AuthScope("localhost", 8080),
	            new UsernamePasswordCredentials("proj_mgr", "faces"));
	            
		GetMethod getMethod = new GetMethod("http://localhost:8080/webapp/projectService/projects");
		int statusCode = httpClient.executeMethod(getMethod);
		assertEquals(200, statusCode);
		
		XPathFactory factory = XPathFactory.newInstance();
		XPath xpath = factory.newXPath();
		String projectId = xpath.evaluate("//item/id", new InputSource(getMethod.getResponseBodyAsStream()));
		assertEquals("1", projectId);

		getMethod = new GetMethod("http://localhost:8080/webapp/projectService/projects");
		statusCode = httpClient.executeMethod(getMethod);
		assertEquals(200, statusCode);
		
		String projectName = xpath.evaluate("//name", new InputSource(getMethod.getResponseBodyAsStream()));
		assertEquals("Inventory Manager 2.0", projectName);
}

}
