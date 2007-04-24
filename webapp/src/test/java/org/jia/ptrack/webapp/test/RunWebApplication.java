package org.jia.ptrack.webapp.test;

import java.io.File;
import java.io.IOException;

import org.apache.tools.ant.taskdefs.Copy;
import org.apache.tools.ant.taskdefs.Jar;
import org.apache.tools.ant.types.ZipFileSet;
import org.codehaus.cargo.container.internal.util.AntUtils;
import org.mortbay.http.HttpException;
import org.mortbay.http.HttpRequest;
import org.mortbay.http.HttpResponse;
import org.mortbay.http.SocketListener;
import org.mortbay.http.handler.AbstractHttpHandler;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.servlet.WebApplicationContext;
import org.mortbay.util.InetAddrPort;

/**
 * This uses Jetty to run the web application thereby avoiding dependencies on
 * plugins etc. as well as the need to package the app The web app contains only
 * the web.xml and custom-components.jar, which is required because the
 * application uses custom JSF components All other files are retrieved by
 * MyWebApplicationContext Classes are found on the classpath used to run this
 * class.
 * 
 * @author cer
 * 
 */
public class RunWebApplication implements WebContainerLauncher {

	private static final String TARGET_WEBAPP_DIR = "target/webapp";

	private static final String TARGET_WEB_APP_LIB_DIR = TARGET_WEBAPP_DIR
			+ "/WEB-INF/lib";

	protected static final String TARGET_WEB_XML = TARGET_WEBAPP_DIR
			+ "/WEB-INF/web.xml";

	public static void main(String[] args) throws Exception {

		new RunWebApplication().run(args);

	}

	public String suite;

	public boolean passed;

	private Server server;

	protected void run(String[] args) throws Exception {

		int port = 8080;
		if (args.length > 0) {
			port = Integer.parseInt(args[0]);
		}
		WebApplicationContext context = null;

		new File(TARGET_WEB_APP_LIB_DIR).mkdirs();
		createJarFile(TARGET_WEB_APP_LIB_DIR + "/custom-components.jar",
				"custom-components/target/classes");
		copyFileToDirectory("../webapp/src/main/webapp/WEB-INF/web.xml",
				TARGET_WEBAPP_DIR + "/WEB-INF/");

		extraMunging();

		server = new Server();
		SocketListener listener = new SocketListener(new InetAddrPort(port));
		server.addListener(listener);
		context = new MyWebApplicationContext(TARGET_WEBAPP_DIR + "/");
		context.setContextPath("/ptrack");
		server.addContext(context);
		context.addHandler(new MyHttpHandler());
		server.start();
		System.out.println("started");
	}

	public void stop() throws InterruptedException {
		if (server.isStarted())
			server.stop();
	}

	class MyHttpHandler extends AbstractHttpHandler {

		public void handle(String pathInContext, String pathParams,
				HttpRequest request, HttpResponse response)
				throws HttpException, IOException {
			System.out.println("handling <" + pathInContext + ">");
			if ("/selenium/postResults".equals(pathInContext)) {
				request.setHandled(true);
				passed = "passed".equals(request.getParameter("result"));
				suite = request.getParameter("suite");
			}
		}

	}

	protected void extraMunging() {
		// Do nothing
	}

	private void copyFileToDirectory(String file, String toDir) {
		Copy copyTask = (Copy) new AntUtils().createAntTask("copy");
		copyTask.setFile(new File(file));
		copyTask.setTodir(new File(toDir));
		copyTask.setOverwrite(true);
		copyTask.execute();

	}

	private void createJarFile(String jarFile, String classesDir) {
		Jar jarTask = (Jar) new AntUtils().createAntTask("jar");
		jarTask.setUpdate(false);
		jarTask.setDestFile(new File(jarFile));
		ZipFileSet zipFileSet = new ZipFileSet();
		File f1 = new File(classesDir);
		if (f1.exists() || f1.isDirectory()) // FIXME
			zipFileSet.setDir(f1);
		else {
			File f2 = new File("../" + classesDir);
			zipFileSet.setDir(f2);
		}
		jarTask.addZipfileset(zipFileSet);
		jarTask.execute();
	}

	public boolean isPassed() {
		return passed;
	}

	public String getSuite() {
		return suite;
	}

	public void run() throws Exception {
		run(new String[0]);
	}

}
