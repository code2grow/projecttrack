package org.jia.ptrack.domain;

import junit.framework.TestCase;

public class RoleTypeTests extends TestCase {

	public void test() {
		assertEquals(RoleType.PROJECT_MANAGER, RoleType.getEnumManager().getInstanceByDescription(RoleType.PROJECT_MANAGER.getDescription()));
		assertEquals(RoleType.PROJECT_MANAGER, RoleType.getEnumManager().getInstanceByValue(RoleType.PROJECT_MANAGER.getValue()));
	}
}
