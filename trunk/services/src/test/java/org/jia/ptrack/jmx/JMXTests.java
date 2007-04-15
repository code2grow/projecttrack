package org.jia.ptrack.jmx;

import java.util.List;

import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.ObjectName;

import org.jia.ptrack.domain.Project;
import org.jia.ptrack.domain.UserFactory;
import org.jia.ptrack.services.ProjectCoordinator;
import org.jia.ptrack.services.SecurityTestUtil;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

public class JMXTests extends AbstractDependencyInjectionSpringContextTests {

	@Override
	protected String[] getConfigLocations() {
		return new String[] { "classpath*:appCtx/**/*.xml",
		"classpath:appCtx/security/testing-acegi-security.xml" };
	}
	
	public void testJMX() throws Exception {
		SecurityTestUtil.setUser(UserFactory.makeProjectManager(null));
		List<MBeanServer> servers = MBeanServerFactory.findMBeanServer(null);
		assertEquals(1, servers.size());
		MBeanServer server = servers.get(0);
		ObjectName mbeanName = new ObjectName("bean:name=ptrack1");
		assertEquals(0L, server.getAttribute(mbeanName, "add"));
		ProjectCoordinator projectCoordinator = (ProjectCoordinator) applicationContext.getBean("projectCoordinator", ProjectCoordinator.class);
		projectCoordinator.add(new Project());
		assertEquals(1L, server.getAttribute(mbeanName, "add"));
	}

}
