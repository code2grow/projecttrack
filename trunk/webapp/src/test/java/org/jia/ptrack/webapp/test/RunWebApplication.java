package org.jia.ptrack.webapp.test;

import java.io.File;

import net.chrisrichardson.umangite.JettyLauncher;

import org.apache.tools.ant.taskdefs.Copy;
import org.apache.tools.ant.taskdefs.Jar;
import org.apache.tools.ant.types.ZipFileSet;
import org.codehaus.cargo.container.internal.util.AntUtils;

/**
 * Start up Jetty using a Web app in the target directory. To enable JSF custom commponents there is a hack to create the custom-components.jar in the  
 * right place
 * 
 * @author cer
 * 
 */
public class RunWebApplication extends JettyLauncher {

	static final String TARGET_WEBAPP_DIR = "target/webapp";

	private static final String TARGET_WEB_APP_LIB_DIR = TARGET_WEBAPP_DIR
			+ "/WEB-INF/lib";

	protected static final String TARGET_WEB_XML = TARGET_WEBAPP_DIR
			+ "/WEB-INF/web.xml";

	public static void main(String[] args) throws Exception {
		RunWebApplication webApp = new RunWebApplication();

		if (args.length > 0) {
			webApp.setPort(Integer.parseInt(args[0]));
		}

		webApp.start();

	}

	@Override
	protected void prepareWarDirectory() {
		new File(TARGET_WEB_APP_LIB_DIR).mkdirs();
		createJarFile(TARGET_WEB_APP_LIB_DIR + "/custom-components.jar",
				"custom-components/target/classes");
		copyFileToDirectory("../webapp/src/main/webapp/WEB-INF/web.xml",
				TARGET_WEBAPP_DIR + "/WEB-INF/");
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

	@Override
	protected String getWebAppDirectory() {
		return TARGET_WEBAPP_DIR;
	}


	@Override
	public String getContextPath() {
		return "ptrack";
	}
	
	@Override
	public String getSrcWebApp() {
		return "src/main/webapp";
	}

}
