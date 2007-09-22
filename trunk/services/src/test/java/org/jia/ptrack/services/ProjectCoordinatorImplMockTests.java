package org.jia.ptrack.services;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import junit.framework.TestCase;

import org.jia.ptrack.domain.Operation;
import org.jia.ptrack.domain.PTrackWorld;
import org.jia.ptrack.domain.Project;
import org.jia.ptrack.domain.ProjectRepository;
import org.jia.ptrack.domain.StateMachineRepository;
import org.jia.ptrack.domain.User;

public class ProjectCoordinatorImplMockTests extends TestCase {

	private ProjectCoordinatorImpl service;
	private ProjectRepository projectRepository;
	private StateMachineRepository stateMachineRepository;
	private SecurityInfoProvider securityInfoProvider;
	private PTrackWorld world;
	private User projectManager;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		world = new PTrackWorld();
		projectManager = world.getItProjectManager();

		projectRepository = createMock(ProjectRepository.class);
		stateMachineRepository = createMock(StateMachineRepository.class);
		securityInfoProvider = createMock(SecurityInfoProvider.class);
		
		service = new ProjectCoordinatorImpl(projectRepository, stateMachineRepository, securityInfoProvider);
	}
	
	private void verifyMocks() {
		verify(projectRepository, stateMachineRepository, securityInfoProvider);
	}
	
	private void replayMocks() {
		replay(projectRepository, stateMachineRepository, securityInfoProvider);
	}

	public void testAdd() {
		Project project = new Project(); 
		
		expect(stateMachineRepository.findTheStateMachine()).andReturn(world.getStateMachine());
		expect(securityInfoProvider.getCurrentUser()).andReturn(projectManager);
		projectRepository.add(project);
		
		replayMocks();
		
		project = service.add(project);

		assertEquals(world.getStateMachine().getInitialStatus(), project.getStatus());
		assertEquals(projectManager, project.getInitiatedBy());
		
		verifyMocks();
	}

	
	public void testChangeStatus() {
		
		Project project = world.getProjectInProposalState();
		expect(projectRepository.merge(project)).andReturn(project);
		expect(securityInfoProvider.getCurrentUser()).andReturn(projectManager);

		replayMocks();
		
		assertTrue(service.changeStatus(project, true, "Cool"));

		assertFalse(project.getHistory().isEmpty());
		
		Operation operation = project.getHistory().get(0);
		assertEquals(projectManager, operation.getUser());
		assertEquals("Cool", operation.getComments());
		assertEquals(world.getStateMachine().getInitialStatus(), operation.getFromStatus());
		assertEquals(world.getStateMachine().getInitialStatus().getApprovalStatus(), operation.getToStatus());
		
		verifyMocks();
	}
}
