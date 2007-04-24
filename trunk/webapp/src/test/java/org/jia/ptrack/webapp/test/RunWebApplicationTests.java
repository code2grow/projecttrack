package org.jia.ptrack.webapp.test;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.server.SeleniumServer;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;

public class RunWebApplicationTests extends AbstractSeleniumTest {


	@Override
	protected String[] getConfigLocations() {
		return new String[] { "appCtxForSelenium/common/*.xml", "appCtxForSelenium/" + getContainerType()
				+ "/**/*.xml", };
	}

	@Test
	public void testCreateProject() throws InterruptedException {
		createProject(selenium);
	}

	@Test(dependsOnMethods="testCreateProject")
	public void testApproveProject() throws InterruptedException {
		browseAroundAndApproveProject(selenium);
	}

	@Test
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
		selenium.select("_id10:typeSelectOne",
				"label=External Desktop Application");
		selenium.type("_id10:requirementsInput", "Chris Richardson");
		selenium.type("_id10:requirementsEmailInput",
				"chris@chrisrichardson.net");
		selenium.click("document.forms[1].elements['_id10:artifactSelect'][3]");
		selenium.type("_id10:commentsInput",
				"This is a great project.\nWe should do it.\n");
		selenium.click("_id10:_id37");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isTextPresent("Inbox - approve or reject projects"));
		assertEquals(projectName, selenium.getText("//tr[3]/td[1]"));
		// This does not work
		// selenium.click("//a[@onclick=\"document.forms['_id10']['_id10:_id14:2:_id31'].value='_id10:_id14:2:_id31';
		// document.forms['_id10'].submit(); return false;\"]");
		selenium.click("_id10:_id14:2:details");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isTextPresent(projectName));
		assertTrue(selenium.isTextPresent("External Desktop Application"));
		assertEquals("Sean Sullivan", selenium.getText("//tr[3]/td[2]/span"));
		assertTrue(selenium.isTextPresent("Chris Richardson"));
		assertTrue(selenium.isTextPresent("chris@chrisrichardson.net"));
		assertTrue(selenium
				.isTextPresent("This is a great project. We should do it."));
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
