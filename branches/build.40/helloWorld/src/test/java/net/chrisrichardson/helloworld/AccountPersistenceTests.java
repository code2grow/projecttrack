package net.chrisrichardson.helloworld;

import junit.framework.TestCase;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.HibernateTemplate;

public class AccountPersistenceTests extends TestCase {

	private ClassPathXmlApplicationContext ctx;
	private HibernateTemplate ht;

	protected void setUp() throws Exception {
		ctx = new ClassPathXmlApplicationContext("/applicationContext.xml");
		ht = (HibernateTemplate) ctx.getBean("hibernateTemplate", HibernateTemplate.class);
	}
	
	public void test() {
		
		double initialBalance = 234.0;
		Account account = new Account(initialBalance);
		
		ht.save(account);
		
		int id = account.getId();
		
		Account account2 = (Account) ht.get(Account.class, new Integer(id));
		
		assertNotNull(account2);
		assertEquals(initialBalance, account2.getBalance(), 0);
		
	}
}
