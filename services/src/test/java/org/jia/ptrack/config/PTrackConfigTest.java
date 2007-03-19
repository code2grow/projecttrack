package org.jia.ptrack.config;

import javax.sql.DataSource;

import junit.framework.TestCase;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.access.BeanFactoryLocator;
import org.springframework.beans.factory.access.BeanFactoryReference;
import org.springframework.context.access.ContextSingletonBeanFactoryLocator;

public class PTrackConfigTest extends TestCase {

	public void test() {
		BeanFactoryLocator locator = ContextSingletonBeanFactoryLocator
				.getInstance("/appCtx/parentAppCtxRef.xml");
		BeanFactoryReference reference = locator.useBeanFactory("parentAppCtx");
		BeanFactory factory = reference.getFactory();
		try {
			assertNotNull(factory.getBean("dataSource", DataSource.class));
		} finally {
			reference.release();
		}

	}
}
