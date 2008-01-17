package org.jia.ptrack.jmx;

import javax.management.MBeanServer;
import javax.management.ObjectName;

import junit.framework.TestCase;

import org.jia.ptrack.domain.Project;
import org.jia.ptrack.domain.UserMother;
import org.jia.ptrack.services.ProjectCoordinator;
import org.jia.ptrack.services.SecurityTestUtil;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JMXTests extends TestCase {

  private ClassPathXmlApplicationContext appCtx;

  protected String[] getConfigLocations() {
    return new String[] { "classpath*:appCtx/common/**/*.xml",
        "classpath*:appCtx/testing/**/*.xml" };
  }

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    // TODO - The caching of Appctxs conflicts badly with registation of the
    // mbeans
    // This test talks to mbeans that were registered by the most recent
    // appCtx, which is possibly different than the one defined by getConfigLocations()
    // As a result the call counting does not behave as expected.
    appCtx = new ClassPathXmlApplicationContext(getConfigLocations());
    ((ClassPathXmlApplicationContext) appCtx).getBeanFactory()
        .autowireBeanProperties(this,
            AutowireCapableBeanFactory.AUTOWIRE_BY_TYPE, false);

  }

  private MBeanServer mbeanServer;

  public void setMbeanServer(MBeanServer mbeanServer) {
    this.mbeanServer = mbeanServer;
  }

  private ProjectCoordinator projectCoordinator;

  public void setProjectCoordinator(ProjectCoordinator projectCoordinator) {
    this.projectCoordinator = projectCoordinator;
  }

  public void testJMX() throws Exception {
    SecurityTestUtil.setUser(UserMother.makeProjectManager(null));
    ObjectName mbeanName = new ObjectName("bean:name=projectCoordinator");
    Long count = (Long) mbeanServer.getAttribute(mbeanName, "add");
    projectCoordinator.add(new Project());
    assertEquals(count + 1, mbeanServer.getAttribute(mbeanName, "add"));
  }

}
