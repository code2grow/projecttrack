package org.jia.ptrack.domain.hibernate;

import org.jia.ptrack.domain.User;
import org.jia.ptrack.domain.UserRepository;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class HibernateUserRepository extends HibernateDaoSupport implements
		UserRepository {

	public User findUser(String login) {
		return (User) DataAccessUtils.uniqueResult(getHibernateTemplate()
				.findByNamedQueryAndNamedParam("User.findByLogin", "login",
						login));
	}

}
