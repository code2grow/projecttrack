package org.jia.ptrack.domain.hibernate;

import net.chrisrichardson.ormunit.hibernate.HibernatePersistenceTests;

public class HibernateInitializerTests extends HibernatePersistenceTests {

	protected String[] getConfigLocations() {
		return HibernatePTrackTestConstants.PTRACK_APP_CTXS;
	}


	public void testInitializer() {
		// Do nothing
	}
}
