package org.jia.ptrack.domain.hibernate;

import org.jia.ptrack.domain.User;
import org.jia.ptrack.domain.UserRepository;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class HibernateUserRepository extends HibernateDaoSupport implements
		UserRepository {

	public User findUser(String login) {
		return (User) getHibernateTemplate().get(User.class, login);
	}

}
