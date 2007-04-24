package org.jia.ptrack.webapp.test;

import org.testng.annotations.Test;

@Test
public class RunDetachedObjectsWebApplicationTests extends
		RunWebApplicationTests {
	
	@Override
	protected String getContainerType() {
		return "detachedJetty";
	}
	/*
	@Test
	@Override
	public void testApproveProject() throws InterruptedException {
		super.testApproveProject();
	}
	
	@Test
	@Override
	public void testCreateProject() throws InterruptedException {
		super.testCreateProject();
	}
	
	@Test
	@Override
	public void testUpperManagementLogin() throws InterruptedException {
		super.testUpperManagementLogin();
	}
	*/

}
