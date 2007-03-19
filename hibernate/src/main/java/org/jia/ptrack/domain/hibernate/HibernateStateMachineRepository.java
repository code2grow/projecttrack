package org.jia.ptrack.domain.hibernate;

import org.jia.ptrack.domain.StateMachine;
import org.jia.ptrack.domain.StateMachineRepository;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class HibernateStateMachineRepository extends HibernateDaoSupport
		implements StateMachineRepository {

	public StateMachine findTheStateMachine() {
		return (StateMachine) DataAccessUtils.uniqueResult(getHibernateTemplate().find("from StateMachine"));
	}

}
