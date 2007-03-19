package org.jia.ptrack.domain.hibernate;


import java.util.Collection;
import java.util.Iterator;

import net.chrisrichardson.ormunit.hibernate.DatabaseInitializer;

import org.jia.ptrack.domain.DefaultStateMachineFactory;
import org.jia.ptrack.domain.Department;
import org.jia.ptrack.domain.Project;
import org.jia.ptrack.domain.ProjectFactory;
import org.jia.ptrack.domain.StateMachine;
import org.jia.ptrack.domain.Status;
import org.jia.ptrack.domain.User;
import org.jia.ptrack.domain.UserFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.orm.hibernate3.HibernateTemplate;

public class HibernateInitializer implements InitializingBean, DatabaseInitializer {

	private HibernateTemplate template;
	private Project project1;
	private Project project2;
	private Project project3;
	private Collection itDepartmentEmployees;
	private Collection marketingDepartmentEmployees;

	public HibernateInitializer(HibernateTemplate template) {
		this.template = template;
	}

	public void afterPropertiesSet() {
		initializeDatabase();
	}

	public void initializeDatabase() {
		StateMachine stateMachine = new DefaultStateMachineFactory()
				.makeStateMachine("default");
		template.save(stateMachine);
		
		Department itDepartment = new Department("IT");
		template.save(itDepartment);
		
		itDepartmentEmployees = UserFactory.makeAllUsers(itDepartment);
		saveUsers(itDepartmentEmployees);
		User projectManager = (User) itDepartmentEmployees.iterator().next();
		Status initialState = stateMachine.getInitialStatus();
		template.save(project1 = ProjectFactory.makeProject1(initialState, projectManager, itDepartmentEmployees));
		template.save(project2 = ProjectFactory.makeProject2(initialState, projectManager));
		template.save(project3 = ProjectFactory.makeProject3(initialState, projectManager));

		Department marketingDepartment = new Department("marketing");
		template.save(marketingDepartment);
		marketingDepartmentEmployees = UserFactory.makeAllUsers2(marketingDepartment);
		saveUsers(marketingDepartmentEmployees);
	}

	private void saveUsers(Collection allUsers) {
		for (Iterator it = allUsers.iterator(); it.hasNext();) {
			User user = (User) it.next();
			template.save(user);
		}
	}

	public Project getProject1() {
		return project1;
	}

	public Project getProject2() {
		return project2;
	}

	public Project getProject3() {
		return project3;
	}

	public Collection getItDepartmentEmployees() {
		return itDepartmentEmployees;
	}

	public Collection getMarketingDepartmentEmployees() {
		return marketingDepartmentEmployees;
	}

	public User getMarketingDepartmentProjectManager() {
		return (User) marketingDepartmentEmployees.iterator().next();
	}

	public User getItDepartmentProjectManager() {
		return (User) itDepartmentEmployees.iterator().next();
	}
	
	
	
}
