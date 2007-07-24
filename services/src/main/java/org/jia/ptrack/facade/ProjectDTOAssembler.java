package org.jia.ptrack.facade;

import java.util.ArrayList;
import java.util.List;

import net.sf.dozer.util.mapping.DozerBeanMapper;

import org.jia.ptrack.domain.Project;
import org.jia.ptrack.domain.User;

public class ProjectDTOAssembler {

	private DozerBeanMapper mapper;
	
	public ProjectDTOAssembler(DozerBeanMapper mapper) {
		this.mapper = mapper;
	}

	public List<ProjectSummary> makeProjectSummaries(List<Project> projects) {
		List<ProjectSummary> result = new ArrayList<ProjectSummary>(projects.size());
		for (Project project : projects) {
			result.add(makeProjectSummary(project));
		}
		return result;
	}

	ProjectSummary makeProjectSummary(Project project) {
		return (ProjectSummary) mapper.map(project, ProjectSummary.class);
	}

	public ProjectDetails makeProjectDetails(Project project, User user) {
		ProjectDetails details = (ProjectDetails ) mapper.map(project, ProjectDetails.class);
		details.setApprovable(project.isApprovable(user));
		details.setRejectable(project.isRejectable(user));
		return details;
	}

}
