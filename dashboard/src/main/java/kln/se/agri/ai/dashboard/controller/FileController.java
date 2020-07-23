package kln.se.agri.ai.dashboard.controller;

import io.swagger.annotations.Api;
import kln.se.agri.ai.commons.model.User;
import kln.se.agri.ai.dashboard.constant.Constants;
import kln.se.agri.ai.dashboard.rest.response.ResponseWrapper;
import kln.se.agri.ai.persistence.service.FileService;
import kln.se.agri.ai.persistence.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by Prasad on 03/06/20.
 */

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value="/api/file")
@Api(value = "file", tags = "File Controller")
public class FileController {

    private final static Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private FileService fileService;

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = "/uploadImage", method = RequestMethod.POST)
    public ResponseWrapper saveImage(@RequestParam("file") MultipartFile file, @RequestParam("image_type") String image_type, Authentication authentication){
        User user = userService.getUserByUsername(authentication.getName());

        List<Map> result = null;
        if(file != null){
            result = fileService.saveImage(file, image_type);
        }
        if(result.size() != 0 || file == null){
            return ResponseWrapper.success(result);
        }else{
            return ResponseWrapper.failWithMessage(messageSource.getMessage(Constants.ErrorMessageCodes.IMAGE_UPLOAD_ERROR_CODE ,null, Locale.ENGLISH),messageSource.getMessage(Constants.ErrorMessageCodes.IMAGE_UPLOAD_ERROR_MESSAGE, null, Locale.ENGLISH));
        }

    }


}
