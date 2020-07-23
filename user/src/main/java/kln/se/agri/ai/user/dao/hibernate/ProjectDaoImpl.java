package kln.se.agri.ai.user.dao.hibernate;

import kln.se.agri.ai.commons.model.Project;
import kln.se.agri.ai.commons.model.SQLQuery;
import kln.se.agri.ai.persistence.dao.ProjectDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Prasad on 12/15/19.
 */

@Repository("projectDao")
@Transactional
public class ProjectDaoImpl extends CRUDDaoImpl<Project> implements ProjectDao {

    private final static Logger logger = LoggerFactory.getLogger(ProjectDaoImpl.class);

    @Override
    public List<Project> getProjectList(Long farmerId) {
        try {
            return (List<Project>) entityManager.createNamedQuery(Project.GET_ALL_PROJECTS_FOR_FARMER)
                    .setParameter("farmerId", farmerId)
                    .getResultList();
        }
        catch (Exception e){
            return null;
        }

    }

    @Override
    public List<String> getProjectWithMonth(int monthCount) {
        try {
            return (List<String>) entityManager.createNamedQuery(Project.GET_PROJECTS_WITH_MONTH)
                    .setParameter("monthCount", monthCount)
                    .getResultList();
        }
        catch (Exception e){
            return null;
        }

    }

    @Override
    public List<SQLQuery> projectCountsForFarmers() {
        try {
            return (List<SQLQuery>) entityManager.createNamedQuery(Project.GET_PROJECT_COUNTS_FOR_FARMERS)
                    .getResultList();
        }
        catch (Exception e){
            return null;
        }
    }

    @Override
    public List<SQLQuery> getCropCounts() {
        try {
            return (List<SQLQuery>) entityManager.createNamedQuery(Project.GET_CROP_COUNTS)
                    .getResultList();
        }
        catch (Exception e){
            return null;
        }
    }

    @Override
    public List<Object[]> getProfitCounts() {
        try {
            return (List<Object[]>) entityManager.createNamedQuery(Project.GET_PROFIT_COUNTS)
                    .getResultList();
        }
        catch (Exception e){
            return null;
        }
    }
}
