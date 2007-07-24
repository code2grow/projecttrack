package org.jia.ptrack.facade;

import java.util.Arrays;
import java.util.Collections;

import junit.framework.TestCase;
import net.sf.dozer.util.mapping.DozerBeanMapper;

import org.jia.ptrack.domain.Project;
import org.jia.ptrack.domain.ProjectMother;
import org.jia.ptrack.domain.UserMother;

public class ProjectDTOAssemblerTests extends
		TestCase {

	ProjectDTOAssembler assembler;


	@Override
	protected void setUp() throws Exception {
		super.setUp();
		DozerBeanMapper mapper = new DozerBeanMapper();
		mapper.setMappingFiles(Collections.singletonList("dozer/dozermapping.xml"));
		assembler = new ProjectDTOAssembler(mapper);
	}

	public void testMakeProjectSummary() {

		Project project2 = ProjectMother.makeProject2();

		ProjectSummary result = assembler.makeProjectSummary(project2);
		assertNotNull(result);
		
		assertEquals(project2.getName(), result.getName());
		assertEquals(project2.getType(), result.getType());
		assertEquals(project2.getStatus().getName(), result.getStatusName());
	}
	
	public void testMakeProjectDetails() {
		
		Project project2 = ProjectMother.makeProject2();

		ProjectDetails result = assembler.makeProjectDetails(project2, UserMother.makeProjectManager());
		assertNotNull(result);
		
		assertEquals(project2.getName(), result.getName());
		assertEquals(project2.getType(), result.getType());
		assertEquals(project2.getStatus().getName(), result.getStatusName());
		assertNotNull(project2.getInitiatedBy());
		assertEquals(project2.getInitiatedBy().getFullName(), result.getInitiatedBy());
		assertEquals(project2.getRequirementsContactEmail(), result.getRequirementsContactEmail());
		assertEquals(project2.getRequirementsContactName(), result.getRequirementsContactName());
		assertEquals(project2.getInitialComments(), result.getInitialComments());
		assertTrue(project2.getArtifacts() != result.getArtifacts());
		assertTrue(Arrays.equals(project2.getArtifacts(), result.getArtifacts()));
		assertFalse(result.isApprovable());
		assertFalse(result.isRejectable());
	}

}
