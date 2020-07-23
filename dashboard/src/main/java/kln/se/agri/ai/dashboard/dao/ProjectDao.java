package kln.se.agri.ai.dashboard.dao;

import kln.se.agri.ai.commons.model.Project;
import kln.se.agri.ai.commons.model.SQLQuery;

import java.util.List;

/**
 * Created by Prasad on 12/15/19.
 */

public interface ProjectDao extends CRUDDao<Project> {
    List<Project> getProjectList(Long id);

    List<String> getProjectWithMonth(int monthCount);

    List<SQLQuery> projectCountsForFarmers();

    List<SQLQuery> getCropCounts();

    List<Object[]> getProfitCounts();
}
