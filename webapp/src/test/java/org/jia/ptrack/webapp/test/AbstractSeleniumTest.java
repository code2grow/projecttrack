package org.jia.ptrack.webapp.test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public abstract class AbstractSeleniumTest {

	protected SeleniumLauncher selenium;

	protected WebContainerLauncher container;


	protected Log logger = LogFactory.getLog(getClass());

	protected ApplicationContext appCtx;
	
	public AbstractSeleniumTest() {
	}

	@BeforeClass
	public void setUp() throws Exception {
		appCtx = new ClassPathXmlApplicationContext(getConfigLocations());
		((ClassPathXmlApplicationContext)appCtx).getBeanFactory().autowireBeanProperties(this, AutowireCapableBeanFactory.AUTOWIRE_BY_TYPE, false);
		
		container.run();
		selenium.startSelenium();
	}

	
	public void setSelenium(SeleniumLauncher selenium) {
		this.selenium = selenium;
	}

	public void setContainer(WebContainerLauncher container) {
		this.container = container;
	}

	@AfterClass
	public void tearDown() throws Exception {
		try {
			container.stop();
		} catch (Throwable e) {
			logger.error(e);
		}
	
		selenium.stopSelenium();
	}

	

	protected String getContainerType() {
		return System.getProperty("container.type", "jetty");
	}

	protected abstract String[] getConfigLocations();

}