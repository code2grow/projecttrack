package org.jia.ptrack.domain.hibernate;


import java.util.Collection;
import java.util.Iterator;

import net.chrisrichardson.ormunit.hibernate.DatabaseInitializer;

import org.jia.ptrack.domain.Department;
import org.jia.ptrack.domain.PTrackWorld;
import org.jia.ptrack.domain.Project;
import org.jia.ptrack.domain.StateMachine;
import org.jia.ptrack.domain.Status;
import org.jia.ptrack.domain.User;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.orm.hibernate3.HibernateTemplate;

public class PtrackDatabaseInitializer implements InitializingBean, DatabaseInitializer {

	private HibernateTemplate template;
  private PTrackWorld world;

	public PtrackDatabaseInitializer(HibernateTemplate template) {
		this.template = template;
	}

	public void afterPropertiesSet() {
		initializeDatabase();
	}

	public void initializeDatabase() {
	  world = new PTrackWorld();
    StateMachine stateMachine = world.getStateMachine();
		template.save(stateMachine);
		
		Department itDepartment = world.getITDepartment();
		
		template.save(itDepartment);
		
		Collection<User> itDepartmentEmployees = world.getAllITDepartmentEmployees();
		saveUsers(itDepartmentEmployees);
		
		template.save(world.getProjectInCompleteState());
		template.save(world.getProjectInRequirementsState());
		template.save(world.getProjectInProposalState());

		Department marketingDepartment = world.getMarketingDepartment();
		template.save(marketingDepartment);
		Collection<User> marketingDepartmentEmployees = world.getMarketingDepartmentEmployees();
		saveUsers(marketingDepartmentEmployees);
	}

	private void saveUsers(Collection allUsers) {
		for (Iterator it = allUsers.iterator(); it.hasNext();) {
			User user = (User) it.next();
			template.save(user);
		}
	}

	public Project getProjectInCompleteState() {
		return world.getProjectInCompleteState();
	}

	public Project getProjectInRequirementsState() {
		return world.getProjectInRequirementsState();
	}

	public Project getProjectInProposalState() {
		return world.getProjectInProposalState();
	}

	public Collection getItDepartmentEmployees() {
		return world.getAllITDepartmentEmployees();
	}

	public Collection getMarketingDepartmentEmployees() {
		return world.getMarketingDepartmentEmployees();
	}

	public User getMarketingDepartmentProjectManager() {
		return world.getMarketingDepartmentProjectManager();
	}

	public User getItDepartmentProjectManager() {
		return world.getItProjectManager();
	}

	public Status getInitialState() {
		return world.getInitialState();
	}
	
	
	
}
