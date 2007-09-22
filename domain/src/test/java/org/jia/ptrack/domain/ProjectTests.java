package org.jia.ptrack.domain;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Arrays;

import junit.framework.TestCase;

public class ProjectTests extends TestCase {
	
	
	private Project p;
	private User projectManager;
	private User businessAnalyst;

	protected void setUp() throws Exception {
		PTrackWorld world = new PTrackWorld();
		projectManager = world.getItProjectManager();
		businessAnalyst = world.getItBusinessAnalyst();
		p = world.getProjectInProposalState();
	}

	public void testProjectApproval() {
		assertEquals(0, p.getHistory().size());
		assertTrue(p.changeStatus(true, projectManager, "Excellent"));
		assertEquals(1, p.getHistory().size());
		assertTrue(p.changeStatus(true, projectManager, "Even better"));
		assertEquals(2, p.getHistory().size());
		assertTrue(p.changeStatus(true, businessAnalyst, "Fantastic"));
		assertEquals(3, p.getHistory().size());
		
		assertEquals(((Operation)p.getHistory().get(0)).getComments(), "Excellent");
		assertEquals(((Operation)p.getHistory().get(1)).getComments(), "Even better");
		assertEquals(((Operation)p.getHistory().get(2)).getComments(), "Fantastic");
	}

	public void testProjectRejection() {
		assertTrue(p.changeStatus(true, projectManager, "Excellent"));
		assertTrue(p.changeStatus(false, projectManager, "Aweful"));
		assertEquals(2, p.getHistory().size());
		
		assertEquals(((Operation)p.getHistory().get(0)).getComments(), "Excellent");
		assertEquals(((Operation)p.getHistory().get(1)).getComments(), "Aweful");

		assertFalse(p.changeStatus(false, projectManager, "Very bad"));
		assertEquals(2, p.getHistory().size());
}

	public void testProjectDisallowedRejection() {
		assertTrue(p.isValidStateChange(true));
		assertFalse(p.isValidStateChange(false));
		assertFalse(p.changeStatus(false, projectManager, "Aweful"));
		assertTrue(p.getHistory().isEmpty());
	}

	public void testProjectApproval_invalidRole() {
		assertEquals(0, p.getHistory().size());
		assertFalse(p.changeStatus(true, businessAnalyst, "Excellent"));
		assertEquals(0, p.getHistory().size());
	}
	
	public void testArtifacts() {
		assertEquals(2, p.getArtifacts().length);
		ArtifactType[] artifacts = new ArtifactType[]{ArtifactType.ARCHITECTURE};
		p.setArtifacts(artifacts);
		assertEquals(1, p.getArtifacts().length);
		assertTrue(Arrays.equals(artifacts, p.getArtifacts()));
	}
	
	public void testRequiredContactEmail() {
		String email1 = "foo@bar.com";
		String email2 = "baz@bar.com";
		p.setRequirementsContactEmail(email1);
		assertEquals(email1, p.getRequirementsContactEmail());
		p.setRequirementsContactEmail(email2);
		assertEquals(email2, p.getRequirementsContactEmail());
	}

	public void testRequiredContactName() {
		String name1 = "Foo Bar";
		String name2 = "Baz Not";
		p.setRequirementsContactName(name1);
		assertEquals(name1, p.getRequirementsContactName());
		p.setRequirementsContactName(name2);
		assertEquals(name2, p.getRequirementsContactName());
	}
	
	public void testSerializability() throws IOException {
		assertEquals(0, p.getHistory().size());
		assertTrue(p.changeStatus(true, projectManager, "Excellent"));

		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream os = new ObjectOutputStream(bos);
		os.writeObject(p);
		os.close();
	}

}
