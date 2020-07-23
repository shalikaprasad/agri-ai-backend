package kln.se.agri.ai.user.service;

import kln.se.agri.ai.commons.model.ImageFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface FileService {

    List<Map> saveImage(MultipartFile request, String image_type);

    ImageFile uploadImage(MultipartFile request);

    String getImagePath(Long id);

}
