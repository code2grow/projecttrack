package org.jia.ptrack.domain;

import java.util.Collection;

// TODO merge ProjectFactory into ProjectMother

public class ProjectMother {

	public static Project makeProject2() {
		StateMachine stateMachine = new DefaultStateMachineFactory()
				.makeStateMachine("default");
	
		Department itDepartment = new Department("IT");
	
		Collection itDepartmentEmployees = UserFactory
				.makeAllUsers(itDepartment);
		User projectManager = (User) itDepartmentEmployees.iterator().next();
		Status initialState = stateMachine.getInitialStatus();
		Project project2 = ProjectFactory.makeProject2(initialState,
				projectManager);
		return project2;
	}

}
