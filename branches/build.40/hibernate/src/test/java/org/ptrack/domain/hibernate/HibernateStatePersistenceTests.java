package org.ptrack.domain.hibernate;

import java.util.HashSet;
import java.util.Set;

import net.chrisrichardson.ormunit.hibernate.HibernatePersistenceTests;
import net.chrisrichardson.util.TxnCallback;

import org.jia.ptrack.domain.DefaultStateMachineFactory;
import org.jia.ptrack.domain.StateMachine;
import org.jia.ptrack.domain.Status;

public class HibernateStatePersistenceTests extends HibernatePersistenceTests {
	protected String[] getConfigLocations() {
		return HibernatePTrackTestConstants.PTRACK_APP_CTXS;
	}

	public void testSaveState() {

		StateMachine stateManager = new DefaultStateMachineFactory()
				.makeStateMachine("default");
		hibernateTemplate.save(stateManager.getInitialStatus());
	}

	public void testTraverseState() {

		StateMachine stateManager = new DefaultStateMachineFactory()
				.makeStateMachine("default");
		final Status initialStatus = stateManager.getInitialStatus();
		hibernateTemplate.save(initialStatus);

		doWithTransaction(new TxnCallback() {

			public void execute() throws Throwable {
				Status s = (Status) load(Status.class, new Integer(
						initialStatus.getId())); // / TODO
				Set visited = new HashSet();
				while (!visited.contains(s.getName())) {
					System.out.println("Visiting " + s.getName());
					visited.add(s.getName());
					s = s.getApprovalStatus();
				}

			}
		});
	}

}
