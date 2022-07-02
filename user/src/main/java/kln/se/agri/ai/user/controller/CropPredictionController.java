package kln.se.agri.ai.user.controller;

import io.swagger.annotations.Api;
import kln.se.agri.ai.commons.model.Crop;
import kln.se.agri.ai.commons.model.ResponseWrapper;
import kln.se.agri.ai.persistence.dto.CropDto;
import kln.se.agri.ai.persistence.service.CropService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Prasad on 12/15/19.
 */

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value="/api/crop_prediction")
@Api(value = "crop_prediction", tags = "Crop Prediction Controller")
public class CropPredictionController {

    private final static Logger logger = LoggerFactory.getLogger(CropPredictionController.class);

    @Autowired
    private CropService cropService;

    @RequestMapping(value = "/getCropPredictionResult", method = RequestMethod.POST)
    public ResponseWrapper getCropPredictionResult(@RequestBody CropDto cropDto) {
        logger.info(">> Get Crop Prediction Details for Crop Prediction Result page");
        ModelMapper modelMapper = new ModelMapper();
        Crop crop = modelMapper.map(cropDto, Crop.class);
        return ResponseWrapper.success(cropService.getCropPredictionResult(crop));
    }

    @RequestMapping(value = "/getBestCropResult", method = RequestMethod.POST)
    public ResponseWrapper getBestCropResult(@RequestBody CropDto cropDto) {
        logger.info(">> Get Best Crop Result for Best Crop Result page");
        ModelMapper modelMapper = new ModelMapper();
        Crop crop = modelMapper.map(cropDto, Crop.class);
        return ResponseWrapper.success(cropService.getBestCropResult(crop));
    }



}
