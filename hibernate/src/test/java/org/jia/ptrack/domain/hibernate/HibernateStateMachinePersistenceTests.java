package org.jia.ptrack.domain.hibernate;

import net.chrisrichardson.ormunit.hibernate.HibernatePersistenceTests;

import org.jia.ptrack.domain.DefaultStateMachineFactory;
import org.jia.ptrack.domain.StateMachine;

public class HibernateStateMachinePersistenceTests extends
		HibernatePersistenceTests {

	// FIXME Consider a base class??
	protected String[] getConfigLocations() {
		return HibernatePTrackTestConstants.PTRACK_APP_CTXS;
	}
	
	public void test() {
		StateMachine sm = new DefaultStateMachineFactory().makeStateMachine("unique" + System.currentTimeMillis());
		save(sm);
		sm = (StateMachine) load(StateMachine.class, new Integer(sm.getId()));
		assertNotNull(sm);
	}
}
