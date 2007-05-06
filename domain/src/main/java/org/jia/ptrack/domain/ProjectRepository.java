package org.jia.ptrack.domain;

import java.util.List;

public interface ProjectRepository {
	public void add(Project project);

	public Project get(int id);

	public Project merge(Project project);

	public List<Project> getAllProjects(ProjectColumnType sortColumn);

	public List<Project> getProjectsWaitingApprovalByRole(RoleType role, ProjectColumnType sortColumn);

}
