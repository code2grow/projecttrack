package net.chrisrichardson.helloworld;

import junit.framework.Test;
import junit.framework.TestSuite;

public class SpringHibernateHelloWorldLabAllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(
				"Test for net.chrisrichardson.helloworld");
		//$JUnit-BEGIN$
		suite.addTestSuite(AccountTests.class);
		suite.addTestSuite(AccountPersistenceTests.class);
		suite.addTestSuite(HibernateAccountRepositoryTests.class);
		suite.addTestSuite(MoneyTransferServiceTests.class);
		//$JUnit-END$
		return suite;
	}

}
