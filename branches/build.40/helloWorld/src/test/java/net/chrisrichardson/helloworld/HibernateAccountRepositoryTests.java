package net.chrisrichardson.helloworld;

import junit.framework.TestCase;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HibernateAccountRepositoryTests extends TestCase {

	
	private HibernateAccountRepository repository;
	private ClassPathXmlApplicationContext ctx;

	protected void setUp() throws Exception {
		ctx = new ClassPathXmlApplicationContext("/applicationContext.xml");
		repository = (HibernateAccountRepository) ctx.getBean("accountRepository", AccountRepository.class);
	}

	public void test() {
		Account account = new Account(0);

		repository.addAccount(account);
		
		Account account2 = repository.findAccount(account.getId());
		
		assertNotNull(account2);
	}
}
