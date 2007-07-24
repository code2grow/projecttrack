package org.jia.ptrack.domain.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.jia.ptrack.domain.Project;
import org.jia.ptrack.domain.ProjectColumnType;
import org.jia.ptrack.domain.ProjectRepository;
import org.jia.ptrack.domain.RoleType;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class HibernateProjectRepository extends HibernateDaoSupport implements
		ProjectRepository {

	public void add(Project project) {
		// pia-lab-method-stub(hibernate-repository)
		getHibernateTemplate().save(project);
	}

	public Project get(int id)  {
		// pia-lab-method-stub(hibernate-repository)
		Project project = (Project) getHibernateTemplate().get(Project.class,
								new Integer(id));
		return project;
	}

	public Project merge(Project project) {
		// pia-lab-method-stub(hibernate-repository)
		return (Project) getHibernateTemplate().merge(project);
	}

	public List<Project> getAllProjects(ProjectColumnType sortColumn) {
		// pia-lab-method-stub(hibernate-repository)
		String queryString = "from Project as p order by p." + computeSortOrder(sortColumn);
		List<Project> projects = getHibernateTemplate().find(queryString);
		return projects;
	}

	protected String computeSortOrder(ProjectColumnType sortColumn) {
		if (sortColumn == null || ProjectColumnType.NAME.equals(sortColumn))
			return "name";
		if (ProjectColumnType.ROLE.equals(sortColumn))
			return "status.role";
		if (ProjectColumnType.STATUS.equals(sortColumn))
			return "status.name";
		if (ProjectColumnType.TYPE.equals(sortColumn))
			return "type";
		throw new UnsupportedOperationException("Unsupported sort order: "
				+ sortColumn);
	}

	public List<Project> getProjectsWaitingApprovalByRole(final RoleType role,
			final ProjectColumnType sortColumn) {
		// pia-lab-method-stub(hibernate-repository)
		return getHibernateTemplate().executeFind(new HibernateCallback() {

			public Object doInHibernate(Session session) {
				Criteria criteria = session.createCriteria(Project.class);
				// create an alias called ‘status’ for the status relationship
				// so that it can be referenced by restrictions and sort order
				criteria.createAlias("status", "status");
				criteria.add(Restrictions.eq("status.role", role));
				criteria.addOrder(Order.asc(computeSortOrder(sortColumn)));
				// If we were caching
				//criteria.setCacheable(true);
				return criteria.list();
			}
		});
	}

}
