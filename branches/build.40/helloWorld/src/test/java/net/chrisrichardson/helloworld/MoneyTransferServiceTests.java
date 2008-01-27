package net.chrisrichardson.helloworld;

import junit.framework.TestCase;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MoneyTransferServiceTests extends TestCase {

	private HibernateAccountRepository repository;
	private ClassPathXmlApplicationContext ctx;
	private MoneyTransferService service;

	protected void setUp() throws Exception {
		ctx = new ClassPathXmlApplicationContext("/applicationContext.xml");
		repository = (HibernateAccountRepository) ctx.getBean("accountRepository", AccountRepository.class);
		service = (MoneyTransferService) ctx.getBean("moneyTransferService", MoneyTransferService.class);
	}

	
	public void testTransfer() throws Exception {
		double initialBalance1 = 10;
		double initialBalance2 = 20;

		Account account1 = new Account(initialBalance1);
		Account account2 = new Account(initialBalance2);
		
		repository.addAccount(account1);
		repository.addAccount(account2);

		int accountId1 = account1.getId();
		int accountId2 = account2.getId();
		
		service.transfer(accountId1, accountId2, 5);

		double balance1 = repository.findAccount(accountId1).getBalance();
		double balance2 = repository.findAccount(accountId2).getBalance();

		assertEquals(initialBalance1 - 5, balance1, 0.0);
		assertEquals(initialBalance2 + 5, balance2, 0.0);

	}

}
