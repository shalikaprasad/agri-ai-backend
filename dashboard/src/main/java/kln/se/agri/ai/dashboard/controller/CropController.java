package kln.se.agri.ai.dashboard.controller;

import io.swagger.annotations.Api;
import kln.se.agri.ai.dashboard.model.Crop;
import kln.se.agri.ai.dashboard.model.ResponseWrapper;
import kln.se.agri.ai.dashboard.service.CropService;
import kln.se.agri.ai.dashboard.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Prasad on 12/15/19.
 */

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value="/api/crop")
@Api(value = "crop", tags = "Crop Controller")
public class CropController {

    private final static Logger logger = LoggerFactory.getLogger(CropController.class);

    @Autowired
    private CropService cropService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/getCropById", method = RequestMethod.GET)
    public ResponseWrapper getNewsByCount(Authentication authentication, @RequestParam("cropId") String id) {
        logger.info(">> Get Crop for home page");
        if(userService.getUserByUsername(authentication.getName()) != null){
            return ResponseWrapper.success(cropService.getCropById(Long.valueOf(id)));
        }
        else {
            return null;
        }
    }

    @RequestMapping(value = "/getAllCrops", method = RequestMethod.GET)
    public ResponseWrapper getAllCrops(Authentication authentication) {
        logger.info(">> Get Crop for home page");
        User user = userService.getUserByUsername(authentication.getName());
        if(user != null){
            return ResponseWrapper.success(cropService.getAllCrops());
        }
        else {
            return null;
        }
    }

    @RequestMapping(value = "/getAllCropNames", method = RequestMethod.GET)
    public ResponseWrapper getAllCropNames(Authentication authentication) {
        logger.info(">> Get Crop for home page");
        User user = userService.getUserByUsername(authentication.getName());
        if(user != null){
            return ResponseWrapper.success(cropService.getAllCropNames());
        }
        else {
            return null;
        }
    }

    @RequestMapping(value = "/updateCrop", method = RequestMethod.POST)
    public ResponseWrapper createProject(@RequestBody CropDto cropDto, Authentication authentication) {
        logger.info(">> Create/Update Crop");
        User user = userService.getUserByUsername(authentication.getName());
        Crop crop = new Crop();
        crop.setIsDeleted(false);
        crop.setCropName(cropDto.getCropName());
        crop.setCropType(cropDto.getCropType());
        crop.setCropPrice(cropDto.getCropPrice());
        crop.setDistrict(cropDto.getDistrict());

        if (cropDto.getId() == null){
            crop.setCreatedUserId(user.getId());
            cropService.createCrop(crop);
        }else {
            crop.setEditedUserId(user.getId());
            crop.setId(cropDto.getId());
            cropService.updateCrop(crop);
        }
        return ResponseWrapper.success("OK");
    }

    @RequestMapping(value = "/deleteCrop", method = RequestMethod.GET)
    public ResponseWrapper deleteCrop(Authentication authentication, @RequestParam("cropId") String id) {
        logger.info(">> Deleting Crop");
        User user = userService.getUserByUsername(authentication.getName());
        if(user != null){
            cropService.deleteCropById(Long.valueOf(id));
            return ResponseWrapper.success("OK");
        }
        else {
            return null;
        }
    }



}
