package kln.se.agri.ai.persistence.service.impl;

import kln.se.agri.ai.commons.model.Crop;
import kln.se.agri.ai.commons.model.Project;
import kln.se.agri.ai.commons.model.SQLQuery;
import kln.se.agri.ai.persistence.dao.CropDao;
import kln.se.agri.ai.persistence.dao.ProjectDao;
import kln.se.agri.ai.persistence.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("projectService")
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectDao projectDao;

    @Autowired
    private CropDao cropDao;

    @Override
    public Project createProject(Project project) {
        return projectDao.create(project);
    }

    @Override
    public Project updateProject(Project project) {
        return projectDao.update(project);
    }

    @Override
    public void deleteProject(Long id) {
        projectDao.delete(id);
    }

    @Override
    public Project find(Long id) {
        return projectDao.find(id);
    }

    @Override
    public List<Project> getProjectList(Long id) {
        List<Project> projectList = projectDao.getProjectList(id);
        return projectList;
    }

    @Override
    public List<Project> getAllProjects() {
        List<Project> projectList = projectDao.listAll();
        return projectList;
    }

    @Override
    public List<SQLQuery> getProjectWithMonth() {
        List<Map<String, Integer>> listOfProject = new ArrayList<>();
        for(int i = 6; i >= 1; i--){
            List<String> projectList = projectDao.getProjectWithMonth(i);
            Set<String> distinct = new HashSet<>(projectList);
            Map<String, Integer> map = new HashMap<>();
            for (String s: distinct) {
                map.put(s, Collections.frequency(projectList, s));
            }
            listOfProject.add(map);
        }
        return setCropList(listOfProject);
    }


    private List<SQLQuery> setCropList(List<Map<String, Integer>> listOfProject){
        List<Crop> cropList = cropDao.getAllCrops();
//        Map<String, int[]> map = new HashMap<>();
        List<SQLQuery> projectList = new ArrayList<>();
        for(Crop crop : cropList) {
            int value = 0;
            int array[] = new int[6];
            for(Map<String, Integer> project : listOfProject) {
                try{
                    array[value] = project.get(crop.getCropName());
                }catch (Exception e){
                    array[value] = 0;
                }
                value++;
            }
            SQLQuery sqlQuery = new SQLQuery(crop.getCropName(), array);
            projectList.add(sqlQuery);
//            map.put(crop.getCropName(), array);
        }
        return projectList;
    }

    @Override
    public List<SQLQuery> getProjectCountByFarmerID() {
        List<SQLQuery> countList = projectDao.projectCountsForFarmers();
        return countList;
    }

    @Override
    public List<SQLQuery> getCropCounts() {
        List<SQLQuery> countList = projectDao.getCropCounts();
        return countList;
    }

    @Override
    public List<Object[]> getProfitCounts() {
        List<Object[]> countList = projectDao.getProfitCounts();
        return countList;
    }
}
