package org.jia.ptrack.security.repository.hibernate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.acegisecurity.acl.basic.AclObjectIdentity;
import org.acegisecurity.acl.basic.BasicAclDao;
import org.acegisecurity.acl.basic.BasicAclEntry;
import org.acegisecurity.acl.basic.NamedEntityObjectIdentity;
import org.acegisecurity.acl.basic.SimpleAclEntry;
import org.jia.ptrack.domain.Project;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.util.Assert;

public class HibernatePTrackAclRepository extends HibernateDaoSupport implements
		BasicAclDao {

	public BasicAclEntry[] getAcls(AclObjectIdentity aclObjectIdentity) {
		Assert.isInstanceOf(NamedEntityObjectIdentity.class, aclObjectIdentity,
				"Only NamedEntityObjectIdentity supported");
		NamedEntityObjectIdentity neoi = (NamedEntityObjectIdentity) aclObjectIdentity;

		if (!neoi.getClassname().startsWith(Project.class.getName())) {
			return null;
		}

		String id = neoi.getId();

		List result = new ArrayList();

		result.add(new SimpleAclEntry("ROLE_UPPER_MANAGER", aclObjectIdentity,
				null, SimpleAclEntry.READ));

		List usersInSameDepartment = getHibernateTemplate()
				.findByNamedParam(
						"select u.login.login from Project p, User u where u.department = p.initiatedBy.department and p.id = :projectId ",
						"projectId", new Integer(id));

		for (Iterator it = usersInSameDepartment.iterator(); it.hasNext();) {
			String login = (String) it.next();
      System.out.println("Adding to acl:" + login);
			SimpleAclEntry aclEntry = new SimpleAclEntry(login,
					aclObjectIdentity, null, SimpleAclEntry.READ
							| SimpleAclEntry.WRITE);
			result.add(aclEntry);

		}

		if (result.isEmpty())
			return null;

		return (BasicAclEntry[]) result.toArray(new BasicAclEntry[] {});
	}
}
