package org.jia.ptrack.domain.hibernate;

import java.util.Iterator;
import java.util.List;

import junit.framework.Assert;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.jia.ptrack.domain.Operation;
import org.jia.ptrack.domain.Project;
import org.jia.ptrack.domain.ProjectColumnType;
import org.jia.ptrack.domain.RoleType;
import org.springframework.orm.hibernate3.HibernateCallback;

// $pia-lab-ignore-file(hibernate-repository)$

public class HibernateProjectRepositoryUsingDetachedObjects extends
		HibernateProjectRepository {

	public List getAllProjects(ProjectColumnType sortColumn) {
		// pia-lab-method-stub(hibernate-repository)
		String queryString = "from Project as p left join fetch p.status "
				+ "order by p." + computeSortOrder(sortColumn);
		List projects = getHibernateTemplate().find(queryString);
		// for (Iterator iter = projects.iterator(); iter.hasNext();) {
		// Project project = (Project) iter.next();
		// detachStuff(project);
		// }
		return projects;
	}

	public List getProjectsWaitingApprovalByRole(final RoleType role,
			final ProjectColumnType sortColumn) {
		// pia-lab-method-stub(hibernate-repository)
		return getHibernateTemplate().executeFind(
				new HibernateCallback() {

					public Object doInHibernate(Session session) {
						Criteria criteria = session
								.createCriteria(Project.class);
						// create an alias called ‘status’ for the status
						// relationship
						// so that it can be referenced by restrictions and sort
						// order
						criteria.createAlias("status", "status");
						criteria.add(Restrictions.eq("status.role", role));
						criteria.addOrder(Order
								.asc(computeSortOrder(sortColumn)));
						criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
						return criteria.list();
					}
				});
	}

	private void detachStuff(Project project) {
		for (Iterator it = project.getHistory().iterator(); it.hasNext();) {
			Operation op = (Operation) it.next();
			Assert.assertNotNull(op.getFromStatus().getName());
			Assert.assertNotNull(op.getToStatus().getName());
			Assert.assertNotNull(op.getUser().getRole());
		}
		Assert.assertTrue(project.getArtifacts().length >= 0);
		Assert.assertNotNull(project.getStatus().getName());
		Assert.assertNotNull(project.getStatus().getRole());
		getHibernateTemplate().initialize(project.getStatus());
	}

}
