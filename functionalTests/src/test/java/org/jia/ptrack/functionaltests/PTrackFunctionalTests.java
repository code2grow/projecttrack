package org.jia.ptrack.functionaltests;

import java.io.File;
import java.net.URL;

import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.cargo.container.deployable.WAR;
import org.codehaus.cargo.container.installer.ZipURLInstaller;
import org.codehaus.cargo.container.property.GeneralPropertySet;
import org.codehaus.cargo.container.property.ServletPropertySet;
import org.codehaus.cargo.container.tomcat.Tomcat5xInstalledLocalContainer;
import org.codehaus.cargo.container.tomcat.Tomcat5xStandaloneLocalConfiguration;
import org.codehaus.cargo.util.log.SimpleLogger;
import org.openqa.selenium.server.SeleniumServer;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;

public class PTrackFunctionalTests extends TestCase {

	private Selenium selenium;

	private Tomcat5xInstalledLocalContainer container;

	private SeleniumServer selServer;

	private Log logger = LogFactory.getLog(getClass());

	public void setUp() throws Exception {

		logger.debug("installing tomcat");

		File tempDir = getTempDir();
		
		ZipURLInstaller installer = new ZipURLInstaller(
				new URL(
						"http://apache.tradebit.com/pub/tomcat/tomcat-5/v5.0.28/bin/jakarta-tomcat-5.0.28.zip"),
				new File(tempDir, "tomcat-install"));
		installer.install();

		logger.debug("starting tomcat");

		Tomcat5xStandaloneLocalConfiguration config = new Tomcat5xStandaloneLocalConfiguration(
				new File(tempDir, "tomcat-deploy"));
		config.setProperty(ServletPropertySet.PORT, Integer.toString(8080));
		config.setLogger(new SimpleLogger());
		config.setProperty(GeneralPropertySet.LOGGING, "high");

		WAR war = new WAR(locateWAR("webapp/target/ptrack.war"));
		war.setContext("ptrack");

		config.addDeployable(war);

		container = new Tomcat5xInstalledLocalContainer(config);
		File home = installer.getHome();
		container.setHome(home);
		container.setOutput(new File("target/tomcat.log"));
		container.setTimeout(15 * 1000);

		container.start();
		logger.debug("starting selenium server");

		selServer = new SeleniumServer();

		selServer.start();
		logger.debug("starting selenium ");

		selenium = new DefaultSelenium("localhost", 4444, "*iexplore",
				"http://localhost:8080/ptrack/");
		selenium.start();
	}

	private File getTempDir() {
		String[] dirs = {"i:\\tmp", "c:\\tmp"};
		for (int i = 0; i < dirs.length; i++) {
			String d = dirs[i];
			File dir = new File(d);
			if (dir.exists())
				return dir;
		}
		return new File(System.getProperty("java.io.tmpdir"));
	}

	private String locateWAR(String path) throws Exception {
		if (new File(path).exists())
			return path;
		path = "../" + path;
		if (new File(path).exists())
			return path;
		throw new Exception("Cannot find path: " + path);
	}

	public void tearDown() throws InterruptedException {
		try {
			container.stop();
		} catch (Throwable e) {
			logger.error(e);
		}

		try {
			selenium.stop();
		} catch (Throwable e) {
			logger.error(e);
		}

		selServer.stop();
	}

	public void testCreateProject() throws InterruptedException {
		createProject(selenium);
	}

	public void testApproveProject() throws InterruptedException {
		browseAroundAndApproveProject(selenium);
	}
	public void testUpperManagementLogin() throws InterruptedException {
		upperManagerLogin(selenium);
	}
	
	public void createProject(Selenium selenium) {
		selenium.open("/ptrack/acegilogin.jsp");
		selenium.type("j_username", "proj_mgr");
		selenium.type("j_password", "faces");
		selenium.click("Login");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isTextPresent("(proj_mgr)"));
		selenium.click("link=Create New");
		selenium.waitForPageToLoad("30000");
		String projectName = "XXX Project" + System.currentTimeMillis();
		selenium.type("_id10:nameInput", projectName);
		selenium.select("_id10:typeSelectOne", "label=External Desktop Application");
		selenium.type("_id10:requirementsInput", "Chris Richardson");
		selenium.type("_id10:requirementsEmailInput", "chris@chrisrichardson.net");
		selenium.click("document.forms[1].elements['_id10:artifactSelect'][3]");
		selenium.type("_id10:commentsInput", "This is a great project.\nWe should do it.\n");
		selenium.click("_id10:_id37");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isTextPresent("Inbox - approve or reject projects"));
		assertEquals(projectName, selenium.getText("//tr[3]/td[1]"));
		// This does not work
//		selenium.click("//a[@onclick=\"document.forms['_id10']['_id10:_id14:2:_id31'].value='_id10:_id14:2:_id31'; document.forms['_id10'].submit(); return false;\"]");
		selenium.click("_id10:_id14:2:details");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isTextPresent(projectName));
		assertTrue(selenium.isTextPresent("External Desktop Application"));
		assertEquals("Sean Sullivan", selenium.getText("//tr[3]/td[2]/span"));
		assertTrue(selenium.isTextPresent("Chris Richardson"));
		assertTrue(selenium.isTextPresent("chris@chrisrichardson.net"));
		assertTrue(selenium.isTextPresent("This is a great project. We should do it."));
		assertEquals("ProjectTrack - Project details", selenium.getTitle());
		selenium.click("_id10:_id46");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Logout");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isTextPresent("Welcome to Project Track"));

	}
	
	public void browseAroundAndApproveProject(Selenium selenium) {
		selenium.open("/ptrack/index.jsp");
		selenium.type("j_username", "proj_mgr");
		selenium.type("j_password", "faces");
		selenium.click("Login");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Details");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Next");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Previous");
		selenium.waitForPageToLoad("30000");
		selenium.click("_id10:_id46");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Approve");
		selenium.waitForPageToLoad("30000");
		selenium.type("_id10:commentsInput", "this is great");
		selenium.click("_id10:_id35");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Approve");
		selenium.waitForPageToLoad("30000");
		selenium.type("_id10:commentsInput", "This is even better");
		selenium.click("_id10:_id35");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Show All");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isTextPresent("Inventory Manager 2.0"));
	}
	
	public void upperManagerLogin(Selenium selenium) {
		selenium.open("/ptrack/acegilogin.jsp");
		selenium.type("j_username", "upper_mgr");
		selenium.type("j_password", "faces");
		selenium.click("Login");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isTextPresent("Show all projects"));
		assertTrue(selenium.isTextPresent("(upper_mgr)"));
		selenium.click("//a[@id='_id10:_id14:2:details']");
		selenium.waitForPageToLoad("30000");
		selenium.click("_id10:_id46");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Details");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Next");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Previous");
		selenium.waitForPageToLoad("30000");
		selenium.click("_id10:_id46");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Show All");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isTextPresent("Show all projects"));
		selenium.click("link=Logout");
		selenium.waitForPageToLoad("30000");

	}


}
