package org.jia.ptrack.webapp.test;

import net.chrisrichardson.selunit.AbstractSeleniumTest;

import org.testng.annotations.Test;


public class RunWebApplicationTests extends AbstractSeleniumTest {


	@Override
	protected String[] getConfigLocations() {
		return new String[] { "appCtxForSelenium/common/*.xml", "appCtxForSelenium/" + getContainerType()
				+ "/**/*.xml", };
	}

	@Test
	public void testCreateProject() throws InterruptedException {
		createProject();
	}

	@Test(dependsOnMethods="testCreateProject")
	public void testApproveProject() throws InterruptedException {
		browseAroundAndApproveProject();
	}

	@Test
	public void testUpperManagementLogin() throws InterruptedException {
		upperManagerLogin();
	}

	public void createProject() {
		login("proj_mgr", "faces");
		assertTextPresent("(proj_mgr)");
		clickAndWait("link=Create New");
		String projectName = "XXX Project" + System.currentTimeMillis();
		enterNewProjectInfo(projectName);
		assertTextPresent("Inbox - approve or reject projects");
		assertTextEquals(projectName, "inboxPage:inboxTable:2:projectName");

		clickAndWait("inboxPage:inboxTable:2:details");
		assertTextPresent(projectName);
		assertTextPresent("External Desktop Application");
		assertTextEquals("Sean Sullivan", "detailsPage:initiatedBy");
		assertTextPresent("Chris Richardson");
		assertTextPresent("chris@chrisrichardson.net");
		assertTextPresent("This is a great project. We should do it.");
		assertTitle("ProjectTrack - Project details");
		clickAndWait("detailsPage:ok");
		clickAndWait("link=Logout");
		assertTextPresent("Welcome to Project Track");
	}

	private void enterNewProjectInfo(String projectName) {
		type("projectDetails:nameInput", projectName);
		select("projectDetails:typeSelectOne",
				"label=External Desktop Application");
		type("projectDetails:requirementsInput", "Chris Richardson");
		type("projectDetails:requirementsEmailInput",
				"chris@chrisrichardson.net");
		click("document.forms[1].elements['projectDetails:artifactSelect'][3]");
		type("projectDetails:commentsInput",
				"This is a great project.\nWe should do it.\n");
		clickAndWait("projectDetails:save");
	}

	private void login(String userId, String password) {
		open("/ptrack/");
		type("j_username", userId);
		type("j_password", password);
		clickAndWait("Login");
	}

	public void browseAroundAndApproveProject() {
		login("proj_mgr", "faces");
		clickAndWait("link=Details");
		clickAndWait("link=Next");
		clickAndWait("link=Previous");
		clickAndWait("detailsPage:ok");
		clickAndWait("inboxPage:inboxTable:0:approve");
		type("approveForm:commentsInput", "this is great");
		clickAndWait("approveForm:approve");
		clickAndWait("inboxPage:inboxTable:0:approve");
		type("approveForm:commentsInput", "This is even better");
		clickAndWait("approveForm:approve");
		clickAndWait("link=Show All");
		assertTextPresent("Inventory Manager 2.0");
		clickAndWait("link=Logout");
	}

	public void upperManagerLogin() {
		login("upper_mgr", "faces");
		assertTextPresent("Show all projects");
		assertTextPresent("(upper_mgr)");
		clickAndWait("showAll:showAllTable:0:details");
		clickAndWait("detailsPage:ok");
		clickAndWait("link=Details");
		clickAndWait("link=Next");
		clickAndWait("link=Previous");
		clickAndWait("detailsPage:ok");
		clickAndWait("link=Show All");
		assertTextPresent("Show all projects");
		clickAndWait("link=Logout");
	}

}
