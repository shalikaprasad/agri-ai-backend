package kln.se.agri.ai.user.service;

import kln.se.agri.ai.commons.model.Project;
import kln.se.agri.ai.commons.model.SQLQuery;

import java.util.List;

public interface ProjectService {

    Project createProject(Project project);

    Project updateProject(Project project);

    void deleteProject(Long id);

    Project find(Long id);

    List<Project> getProjectList(Long id);

    List<Project> getAllProjects();

    List<SQLQuery> getProjectWithMonth();

    List<SQLQuery> getProjectCountByFarmerID();

    List<SQLQuery> getCropCounts();

    List<Object[]> getProfitCounts();
}
