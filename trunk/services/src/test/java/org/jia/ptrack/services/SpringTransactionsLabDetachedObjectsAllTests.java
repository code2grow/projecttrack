package org.jia.ptrack.services;

import junit.framework.Test;
import junit.framework.TestSuite;

public class SpringTransactionsLabDetachedObjectsAllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for org.jia.ptrack.services");
		//$JUnit-BEGIN$
		suite.addTestSuite(ProjectFetchJoinHQLTests.class);
		suite.addTestSuite(ProjectCoordinatorDetachedObjectTests.class);
		//$JUnit-END$
		return suite;
	}

}
