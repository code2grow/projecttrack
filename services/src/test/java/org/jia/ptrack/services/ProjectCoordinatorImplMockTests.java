package org.jia.ptrack.services;

import org.jia.ptrack.domain.Operation;
import org.jia.ptrack.domain.PTrackWorld;
import org.jia.ptrack.domain.Project;
import org.jia.ptrack.domain.ProjectRepository;
import org.jia.ptrack.domain.StateMachineRepository;
import org.jia.ptrack.domain.User;
import org.jia.ptrack.domain.UserRepository;
import static org.easymock.EasyMock.*;

import junit.framework.TestCase;

public class ProjectCoordinatorImplMockTests extends TestCase {

	private ProjectCoordinatorImpl service;
	private ProjectRepository projectRepository;
	private StateMachineRepository stateMachineRepository;
	private UserRepository userRepository;
	private SecurityInfoProvider securityInfoProvider;
	private PTrackWorld world;
	private User projectManager;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		world = new PTrackWorld();
		projectManager = world.getProjectManager();

		projectRepository = createMock(ProjectRepository.class);
		stateMachineRepository = createMock(StateMachineRepository.class);
		userRepository = createMock(UserRepository.class);
		securityInfoProvider = createMock(SecurityInfoProvider.class);
		
		service = new ProjectCoordinatorImpl(projectRepository, stateMachineRepository, userRepository, securityInfoProvider);
	}
	
	private void verifyMocks() {
		verify(projectRepository, stateMachineRepository, userRepository, securityInfoProvider);
	}
	
	private void replayMocks() {
		replay(projectRepository, stateMachineRepository, userRepository, securityInfoProvider);
	}

	public void testAdd() {
		Project project = new Project(); 
		
		expect(stateMachineRepository.findTheStateMachine()).andReturn(world.getStateMachine());
		expect(securityInfoProvider.getUsername()).andReturn(projectManager.getLogin());
		expect(userRepository.findUser(projectManager.getLogin())).andReturn(projectManager);
		projectRepository.add(project);
		
		replayMocks();
		
		project = service.add(project);

		assertEquals(world.getStateMachine().getInitialStatus(), project.getStatus());
		assertEquals(projectManager, project.getInitiatedBy());
		
		verifyMocks();
	}

	
	public void testChangeStatus() {
		
		Project project = world.getProject();
		Project project2 = world.getProject2();
		
		expect(projectRepository.merge(project)).andReturn(project2);
		expect(securityInfoProvider.getUsername()).andReturn(projectManager.getLogin());
		expect(userRepository.findUser(projectManager.getLogin())).andReturn(projectManager);

		replayMocks();
		
		assertTrue(service.changeStatus(project, true, "Cool"));

		assertTrue(project.getHistory().isEmpty());
		assertFalse(project2.getHistory().isEmpty());
		
		Operation operation = project2.getHistory().get(0);
		assertEquals(projectManager, operation.getUser());
		assertEquals("Cool", operation.getComments());
		assertEquals(world.getStateMachine().getInitialStatus(), operation.getFromStatus());
		assertEquals(world.getStateMachine().getInitialStatus().getApprovalStatus(), operation.getToStatus());
		
		verifyMocks();
	}
}
