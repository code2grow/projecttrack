package org.jia.ptrack.domain;

import java.util.List;

public interface ProjectRepository {
	public void add(Project project) throws DataStoreException;

	public Project get(String id) throws ObjectNotFoundException,
			DataStoreException;

	public Project update(Project project) throws ObjectNotFoundException,
			DataStoreException;

	public List getAllProjects(ProjectColumnType sortColumn)
			throws ObjectNotFoundException, DataStoreException;

	public List getProjectsWaitingApprovalByRole(RoleType role, ProjectColumnType sortColumn)
			throws ObjectNotFoundException, DataStoreException;

}
