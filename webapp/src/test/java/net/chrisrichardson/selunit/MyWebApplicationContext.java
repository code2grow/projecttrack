package net.chrisrichardson.selunit;

import java.io.IOException;

import org.mortbay.jetty.servlet.WebApplicationContext;
import org.mortbay.util.Resource;

public class MyWebApplicationContext extends WebApplicationContext {

	private Resource realWebApp;

	public MyWebApplicationContext(String name) {
		super(name);
	}
	

	public Resource getResource(String resourceName) throws IOException {
		if ("/WEB-INF/web.xml".equals(resourceName))
			return super.getResource(resourceName);
		if (realWebApp == null)
			realWebApp = Resource.newResource("../webapp/src/main/webapp");
		Resource r = realWebApp.addPath(resourceName);
		if (r.exists()) {
			// System.out.println("loading from real web app: " + resourceName);
			return r;
		}
		//System.out.println("loading resource: " + resourceName);
		Resource resource = super.getResource(resourceName);
		return resource;
	}
	

}
