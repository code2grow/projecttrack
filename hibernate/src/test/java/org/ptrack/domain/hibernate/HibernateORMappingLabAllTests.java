package org.ptrack.domain.hibernate;

import junit.framework.Test;
import junit.framework.TestSuite;

public class HibernateORMappingLabAllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for org.ptrack.domain.hibernate");
		//$JUnit-BEGIN$
		suite.addTestSuite(HibernateProjectSimpleFieldTests.class);
		suite.addTestSuite(HibernateProjectComponentFieldTests.class);
		suite.addTestSuite(HibernateProjectOneToManyTests .class);
		suite.addTestSuite(HibernateProjectManyToOneTests .class);
		suite.addTestSuite(HibernateProjectUserTypeFieldTests .class);
		//$JUnit-END$
		return suite;
	}

}
