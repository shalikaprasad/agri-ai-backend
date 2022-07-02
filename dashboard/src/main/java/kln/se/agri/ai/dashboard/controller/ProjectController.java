package kln.se.agri.ai.dashboard.controller;

import io.swagger.annotations.Api;
import kln.se.agri.ai.commons.model.*;
import kln.se.agri.ai.persistence.dto.ProjectDto;
import kln.se.agri.ai.persistence.service.CropService;
import kln.se.agri.ai.persistence.service.ProjectService;
import kln.se.agri.ai.persistence.service.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Prasad on 12/15/19.
 */

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value="/api/project")
@Api(value = "project", tags = "Project Controller")
public class ProjectController {

    private final static Logger logger = LoggerFactory.getLogger(ProjectController.class);

    @Autowired
    private ProjectService projectService;

    @Autowired
    private CropService cropService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/getProjectById", method = RequestMethod.POST)
    public ResponseWrapper getProjectById(@RequestBody String projectId, Authentication authentication) {
        logger.info(">> Get Project");
        User user = userService.getUserByUsername(authentication.getName());
        if(user != null){
            return ResponseWrapper.success(projectService.find(Long.valueOf(projectId)));
        }else {
            return ResponseWrapper.failWithMessage("Cannot Find");
        }
    }

    @RequestMapping(value = "/getProjectsForUser", method = RequestMethod.POST)
    public ResponseWrapper getProjectsForUser(@RequestBody String farmerId, Authentication authentication) {
        logger.info(">> Get get Projects For User");
        User user = userService.getUserByUsername(authentication.getName());
        if(user != null){
//            Farmer farmer = userService.getFarmer(Long.valueOf(farmerId));
            List<Project> projectList = projectService.getProjectList(Long.valueOf(farmerId));

            return ResponseWrapper.success(projectList);
        }else {
            return ResponseWrapper.failWithMessage("Cannot Find");
        }

    }

    @RequestMapping(value = "/getAllProjects", method = RequestMethod.GET)
    public ResponseWrapper getAllProjects(Authentication authentication) {
        logger.info(">> Get get All Projects");
        User user = userService.getUserByUsername(authentication.getName());
        if(user != null){
            List<Project> projectList = projectService.getAllProjects();

            return ResponseWrapper.success(projectList);
        }else {
            return ResponseWrapper.failWithMessage("Cannot Find");
        }

    }

    @RequestMapping(value = "/createProject", method = RequestMethod.POST)
    public ResponseWrapper createProject(@RequestBody ProjectDto projectDto, Authentication authentication) throws ParseException {
        logger.info(">> Create Project Start");
        User user = userService.getUserByUsername(authentication.getName());
        ModelMapper modelMapper = new ModelMapper();
        Project project = modelMapper.map(projectDto, Project.class);
        project.setIsDeleted(false);
        Long farmerId = Long.valueOf(projectDto.getFarmer().getId());
        Farmer farmer = userService.getFarmer(farmerId);
        project.setFarmer(farmer);
        String cropName = projectDto.getCropName();
        String idNumber = farmer.getIdNumber();
//        project.setCropName(cropName);
//        project.setIdNumber(idNumber);
        Crop crop = cropService.getCropByName(cropName);
        project.setCrop(crop);
        project.setFail(projectDto.isFail());
        project.setExpired(projectDto.isExpired());
        Project project1 = null;
        if(projectDto.getId() == null){
            project.setCreatedUserId(user.getId());
            project1 = projectService.createProject(project);
        }else {
            Date today = new Date();
            project.setUpdateDate(today);
            if(projectDto.isExpired()){
                project.setExpireDate(today);
            }
            project.setId(projectDto.getId());
            project1 = projectService.updateProject(project);
        }

        if(project1 != null){
            return ResponseWrapper.success("OK");
        }else {
            return null;
        }
    }

    @RequestMapping(value = "/getProjectsForRequiredMonth", method = RequestMethod.GET)
    public ResponseWrapper getProjectsForRequiredMonth(Authentication authentication) {
        logger.info(">> Get get Projects For Required Month");
        User user = userService.getUserByUsername(authentication.getName());
        if(user != null){
            List<SQLQuery> list = projectService.getProjectWithMonth();
            return ResponseWrapper.success(list);
        }else {
            return ResponseWrapper.failWithMessage("Cannot Find");
        }

    }

    @RequestMapping(value = "/getProjectCountByFarmerID", method = RequestMethod.GET)
    public ResponseWrapper getProjectCountByFarmerID(Authentication authentication) {
        logger.info(">> Get Project Count By Farmer ID");
        User user = userService.getUserByUsername(authentication.getName());
        if(user != null){
            List<SQLQuery> getProjectCountsList = projectService.getProjectCountByFarmerID();
            SQLQuery biggerList = null;
            long value = 0L;
            for(SQLQuery farmerProjectCount : getProjectCountsList){
                if(value < farmerProjectCount.getTotal()){
                    value = farmerProjectCount.getTotal();
                    biggerList = farmerProjectCount;
                }
            }
            for(SQLQuery count : getProjectCountsList){
                if(biggerList != null && biggerList.equals(count)){
                    count.setBigger(true);
                    break;
                }
            }
            return ResponseWrapper.success(getProjectCountsList);
        }else {
            return ResponseWrapper.failWithMessage("Cannot Find");
        }

    }

    @RequestMapping(value = "/getCropCounts", method = RequestMethod.GET)
    public ResponseWrapper getCropCounts(Authentication authentication) {
        logger.info(">> Get Crop Counts");
        User user = userService.getUserByUsername(authentication.getName());
        if(user != null){
            List<SQLQuery> countsList = projectService.getCropCounts();
            SQLQuery biggerList = null;
            long value = 0L;
            for(SQLQuery count : countsList){
                if(value < count.getTotal()){
                    value = count.getTotal();
                    biggerList = count;
                }
            }
            for(SQLQuery count : countsList){
                if(biggerList != null && biggerList.equals(count)){
                    count.setBigger(true);
                    break;
                }
            }
            return ResponseWrapper.success(countsList);
        }else {
            return ResponseWrapper.failWithMessage("Cannot Find");
        }
    }

    @RequestMapping(value = "/getProfitCounts", method = RequestMethod.GET)
    public ResponseWrapper getProfitCounts(Authentication authentication) {
        logger.info(">> Get Profit Counts");
        User user = userService.getUserByUsername(authentication.getName());
        if(user != null){
            List<Object[]> countsList = projectService.getProfitCounts();
            List<SQLQuery> profitList = new ArrayList<>();

            SQLQuery biggerList = null;
            Double value = 0.0;
            for(int i = 0; i < countsList.size(); i++){
                String name = (String) countsList.get(i)[0];
                Double number = (Double) countsList.get(i)[1];
                SQLQuery sqlQuery = new SQLQuery(name, number);
                if(value < number){
                    value = number;
                    biggerList = sqlQuery;
                }
                profitList.add(sqlQuery);
            }
            for(SQLQuery count : profitList){
                if(biggerList != null && biggerList.equals(count)){
                    count.setBigger(true);
                    break;
                }
            }
            return ResponseWrapper.success(profitList);
        }else {
            return ResponseWrapper.failWithMessage("Cannot Find");
        }
    }
}
