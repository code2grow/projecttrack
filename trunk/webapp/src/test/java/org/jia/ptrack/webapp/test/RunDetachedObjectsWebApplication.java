package org.jia.ptrack.webapp.test;

import java.io.File;

import org.apache.tools.ant.taskdefs.Replace;
import org.codehaus.cargo.container.internal.util.AntUtils;

public class RunDetachedObjectsWebApplication extends RunWebApplication {

	public static void main(String[] args) throws Exception {

		new RunDetachedObjectsWebApplication().run();

	}

	protected void extraMunging() {
		// TODO It would be nice to verify that this succeeded

		replace(
				TARGET_WEB_XML,
				"<filter-mapping><filter-name>OpenSessionInViewFilter</filter-name><url-pattern>/*</url-pattern></filter-mapping>",
				"");
		replace(TARGET_WEB_XML, "classpath*:/appCtx/**/*.xml",
				"classpath*:/appCtx/**/*.xml classpath*:/appCtxForDetachedObjects/**/*.xml");
	}

	protected void replace(String file, String fromString, String toString) {
		Replace replaceTask = (Replace) new AntUtils().createAntTask("replace");
		replaceTask.setFile(new File(file));
		replaceTask.setToken(fromString);
		replaceTask.setValue(toString);
		replaceTask.setSummary(true);
		replaceTask.execute();
	}

}
