package org.jia.ptrack.domain.hibernate;

import junit.framework.Test;
import junit.framework.TestSuite;

public class HibernateRepositoryLabAllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(
				"Test for org.jia.ptrack.domain.hibernate");
		//$JUnit-BEGIN$
		suite.addTestSuite(HibernateProjectRepositoryTests.class);
		//$JUnit-END$
		return suite;
	}

}
