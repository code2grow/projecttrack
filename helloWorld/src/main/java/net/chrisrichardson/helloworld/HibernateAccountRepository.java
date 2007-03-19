package net.chrisrichardson.helloworld;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class HibernateAccountRepository extends HibernateDaoSupport implements AccountRepository {

	
	public void addAccount(Account account) {
		// pia-lab-method-stub(hello-world)
		getHibernateTemplate().save(account);
	}

	public Account findAccount(int accountId) {
		// pia-lab-method-stub(hello-world)
		return (Account) getHibernateTemplate().get(Account.class, new Integer(accountId));
	}

}
