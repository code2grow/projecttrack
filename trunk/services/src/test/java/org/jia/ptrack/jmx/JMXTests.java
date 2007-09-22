package org.jia.ptrack.jmx;

import java.util.List;

import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.ObjectName;

import org.jia.ptrack.domain.Project;
import org.jia.ptrack.domain.UserMother;
import org.jia.ptrack.services.ProjectCoordinator;
import org.jia.ptrack.services.SecurityTestUtil;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

public class JMXTests extends AbstractDependencyInjectionSpringContextTests {

	@Override
	protected String[] getConfigLocations() {
		return new String[] { "classpath*:appCtx/common/**/*.xml",
		"classpath*:appCtx/testing/**/*.xml" };
	}
	
	public void testJMX() throws Exception {
		SecurityTestUtil.setUser(UserMother.makeProjectManager(null));
		List<MBeanServer> servers = MBeanServerFactory.findMBeanServer(null);
		assertEquals(1, servers.size());
		MBeanServer server = servers.get(0);
		ObjectName mbeanName = new ObjectName("bean:name=projectCoordinator");
		Long count = (Long)server.getAttribute(mbeanName, "add");
		assertEquals(count, server.getAttribute(mbeanName, "add"));
		ProjectCoordinator projectCoordinator = (ProjectCoordinator) applicationContext.getBean("projectCoordinator", ProjectCoordinator.class);
		projectCoordinator.add(new Project());
		assertEquals(count + 1, server.getAttribute(mbeanName, "add"));
	}

}
