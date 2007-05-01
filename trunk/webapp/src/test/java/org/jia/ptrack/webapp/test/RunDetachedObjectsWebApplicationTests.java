package org.jia.ptrack.webapp.test;

import org.testng.annotations.Test;

@Test
public class RunDetachedObjectsWebApplicationTests extends
		RunWebApplicationTests {
	
	@Override
	protected String getContainerType() {
		return "detachedJetty";
	}

}
