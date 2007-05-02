package org.jia.ptrack.domain;

import java.util.List;

public interface ProjectRepository {
	public void add(Project project);

	public Project get(int id);

	public Project merge(Project project);

	public List getAllProjects(ProjectColumnType sortColumn);

	public List getProjectsWaitingApprovalByRole(RoleType role, ProjectColumnType sortColumn);

}
