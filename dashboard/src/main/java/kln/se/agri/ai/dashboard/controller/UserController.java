package kln.se.agri.ai.dashboard.controller;

import io.swagger.annotations.Api;
import kln.se.agri.ai.commons.model.Farmer;
import kln.se.agri.ai.commons.model.Role;
import kln.se.agri.ai.commons.model.User;
import kln.se.agri.ai.dashboard.rest.response.ResponseWrapper;
import kln.se.agri.ai.persistence.dto.FarmerDto;
import kln.se.agri.ai.persistence.dto.UserDto;
import kln.se.agri.ai.persistence.service.FileService;
import kln.se.agri.ai.persistence.service.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Prasad on 12/15/19.
 */

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value="/api/user")
@Api(value = "user", tags = "User Controller")
public class UserController {

    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private FileService fileService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    public ResponseWrapper getUser(Authentication authentication) {
        logger.info(">> Getting User");
        User user = userService.getUserByUsername(authentication.getName());
        if(user.getPictureId() != null)
        user.setImageFileName(fileService.getImagePath(user.getPictureId()));
        return ResponseWrapper.success(user);
    }

    @RequestMapping(value = "/getAllUsers", method = RequestMethod.GET)
    public ResponseWrapper getAllUsers(Authentication authentication) {
        logger.info(">> Getting User");
        User user = userService.getUserByUsername(authentication.getName());
        if(user != null){
            List<User> userList = userService.getAllUsers();
            for (User user1 : userList){
                if(user1.getPictureId() != null){
                    user1.setImageFileName(fileService.getImagePath(user1.getPictureId()));
                }
            }
            return ResponseWrapper.success(userList);
        }
        else {
            return null;
        }
    }

    @RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
    public ResponseWrapper deleteUser(Authentication authentication, @RequestParam("userId") String id) {
        logger.info(">> Getting User");
        User user = userService.getUserByUsername(authentication.getName());
        if(user != null){
            return ResponseWrapper.success(userService.deleteUser(Long.valueOf(id)));
        }
        else {
            return null;
        }
    }

    @RequestMapping(value = "/createUser", method = RequestMethod.POST)
    public ResponseWrapper createUser(Authentication authentication, @RequestBody UserDto userDto) {
        logger.info(">> Getting User");
        User user = userService.getUserByUsername(authentication.getName());

        if(user != null){
            userDto.setCreatedUserId(user.getId());
            ModelMapper modelMapper = new ModelMapper();
            User user1 = modelMapper.map(userDto, User.class);
            user1.setId(userDto.getId());
            user1.setIs_deleted(false);
            Role role = userService.getRoleByRoleName(userDto.getRoles());
            List<Role> roleList = new ArrayList<>();
            roleList.add(role);
            user1.setRoles(roleList);
            user1.setPassword(passwordEncoder.encode(userDto.getPassword()));
            return ResponseWrapper.success(userService.createUser(user1));
        }
        else {
            return null;
        }
    }


    @RequestMapping(value = "/getAllFarmers", method = RequestMethod.GET)
    public ResponseWrapper getAllFarmers(Authentication authentication) {
        logger.info(">> Getting All Farmers");
        User user = userService.getUserByUsername(authentication.getName());
        if(user != null){
            List<Farmer> farmerList = userService.getAllFarmers();
            return ResponseWrapper.success(farmerList);
        }
        else {
            return null;
        }
    }

    @RequestMapping(value = "/deleteFarmer", method = RequestMethod.GET)
    public ResponseWrapper deleteFarmer(Authentication authentication, @RequestParam("farmerId") String id) {
        logger.info(">> Deleting Farmer");
        User user = userService.getUserByUsername(authentication.getName());
        if(user != null){
            return ResponseWrapper.success(userService.deleteFarmer(Long.valueOf(id)));
        }
        else {
            return null;
        }
    }

    @RequestMapping(value = "/createFarmer", method = RequestMethod.POST)
    public ResponseWrapper createFarmer(Authentication authentication, @RequestBody FarmerDto farmerDto) {
        logger.info(">> Create Farmer");
        User user = userService.getUserByUsername(authentication.getName());

        if(user != null){
            ModelMapper modelMapper = new ModelMapper();
            Farmer farmer = modelMapper.map(farmerDto, Farmer.class);
            farmer.setIs_deleted(false);
            return ResponseWrapper.success(userService.createFarmer(farmer, user.getId()));
        }
        else {
            return null;
        }
    }


}
