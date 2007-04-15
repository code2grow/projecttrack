package org.jia.ptrack.domain.hibernate;

import net.chrisrichardson.ormunit.hibernate.HibernateMappingTests;

import org.jia.ptrack.domain.Operation;
import org.jia.ptrack.domain.Project;
import org.jia.ptrack.domain.StateMachine;
import org.jia.ptrack.domain.Status;
import org.jia.ptrack.domain.User;

public class PTrackHibernateMappingTests extends HibernateMappingTests {

	protected String[] getConfigLocations() {
		return HibernatePTrackTestConstants.PTRACK_APP_CTXS;
	}

	/**
	 * This test verifies that the simple fields of Project are mapped.
	 */
/*	public void testProjectMapped_simpleFields() {
		assertClassMapping(Project.class, "Project");
		// TODO - we could make assertions about specific fields
		assertAllFieldsMappedExcept(SetUtil.make("operationHistory",
				"artifacts", "type", "status", "initialStatus", "idMap"));
	}
*/
	/**
	 * This test verifies the one-to-many relationship Project-Operation is
	 * mapped
	 */
/*	public void testProjectFieldsMapped_operationHistory() {
		assertClassMapping(Project.class, "Project");
		assertAllFieldsMappedExcept(SetUtil.make("artifacts", "type", "status",
				"initialStatus", "idMap"));
		assertClassMapping(Operation.class, "Operation"); // TODO - need to
		// make this case
		// insensitive
		assertAllFieldsMappedExcept(SetUtil.make("toStatus", "fromStatus",
				"user"));
	}
*/
	// TODO - Get ORM to generate nice error messages, suggest type of
	// relationship

	/**
	 * This test verifies the many-to-one relationship Project-ProjectType is
	 * mapped
	 */
/*	public void testProjectFieldsMapped_projectType() {
		assertClassMapping(Project.class, "Project");
		assertAllFieldsMappedExcept(SetUtil.make("artifacts", "status",
				"initialStatus", "idMap"), true);
		assertClassMapping(ProjectType.class, "ProjectType");
		assertAllFieldsMapped(true); //
	}
*/
	public void testThatStateClassIsMapped() {
		assertClassMapping(Status.class, "Status");
		assertAllFieldsMapped();
	}

	/**
	 * This test verifies that the simple fields of Project are mapped.
	 */
	public void testProjectMapped() {
		assertClassMapping(Project.class, "PROJECT");
		// TODO - we could make assertions about specific fields
		assertAllFieldsMapped();
	}

	public void testOperationMapped() {
		assertClassMapping(Operation.class, "Operation");
		// TODO - we could make assertions about specific fields
		assertAllFieldsMapped();
	}

	public void testUserMapped() {
		assertClassMapping(User.class, "Users");
		// TODO - we could make assertions about specific fields
		assertAllFieldsMapped();
	}
	public void testStateMachineMapped() {
		assertClassMapping(StateMachine.class, "StateMachine");
		assertAllFieldsMapped();
	}
	
	public void testAllMapped() {
		assertAllClassesMapped();
	}

}
