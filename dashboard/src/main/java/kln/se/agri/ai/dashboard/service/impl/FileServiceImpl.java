package kln.se.agri.ai.dashboard.service.impl;

import kln.se.agri.ai.commons.model.ImageFile;
import kln.se.agri.ai.persistence.constant.Constants;
import kln.se.agri.ai.persistence.dao.ImageFileDao;
import kln.se.agri.ai.persistence.dao.NewsDao;
import kln.se.agri.ai.persistence.exceptions.ResourceNotFoundException;
import kln.se.agri.ai.persistence.service.FileService;
import net.coobird.thumbnailator.Thumbnailator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Service("fileService")
public class FileServiceImpl implements FileService {

    private final static Logger logger = LoggerFactory.getLogger(FileService.class);

    @Autowired
    private FileService fileService;

    @Value("${image.source}")
    private String imageSource;

    @Value("${pathSeparator}")
    private String pathSeparator;

    @Autowired
    private ImageFileDao imageFileDao;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private NewsDao newsDao;

    @Override
    public List<Map> saveImage(MultipartFile request, String image_type) {
        List<Map> imageMapList = new ArrayList<>();
        ImageFile newFile = uploadImage(request);

        Map<String,String> map = new HashMap<>();
        ImageFile image = saveImageFile(newFile,newFile.getThumbnail(), image_type);
        map.put("id",image.getId().toString());
        map.put("imageName",image.getPictureName());
        map.put("imageFileName",image.getFilename(image.getId()));

        Map<String,byte[]> map2 = new HashMap<>();
        map2.put("thumbnail",image.getThumbnail());
        map2.put("imageFile",image.getFile());

        imageMapList.add(map);
        imageMapList.add(map2);

        return imageMapList;
    }

    @Transactional
    @Override
    public ImageFile uploadImage(MultipartFile multipartFile){

        ImageFile newFile = null;
        try {
//                    String imageName = multipartFile.getName();
                    String mimeType = multipartFile.getContentType();
                    byte[] bytes = multipartFile.getBytes();
                    BufferedImage img = ImageIO.read(new ByteArrayInputStream(bytes));
                    BufferedImage thubnail = Thumbnailator.createThumbnail(img,50,50);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    ImageIO.write(thubnail, "png", baos);
                    byte[] thumbnail = baos.toByteArray();
                    UUID uuid = UUID.randomUUID();
                    String randomUUIDString = uuid.toString();
                    String filename = randomUUIDString;
                    String pictureName = multipartFile.getOriginalFilename();
                    newFile = new ImageFile(filename,pictureName, bytes, mimeType,thumbnail);
                    logger.info(">> Image file uploaded Successfully");

    } catch (IOException ex){
            System.out.println("Error in GIF from existing images");
        }
        catch (Exception e) {
            return  newFile;
        }
        return newFile;
    }

    @Transactional
    public ImageFile saveImageFile(ImageFile imageFile,byte[] thumbnail, String image_type){
        imageFile.setCreatedDate(new Date());
        ImageFile newImageFile = imageFileDao.create(imageFile);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        String base64String = null;
        String image_path_default = imageSource + image_type + "/";
        File dir = new File(image_path_default + imageFile.getFilename(imageFile.getId()).split("/")[0]);
        if(!dir.exists()){
            dir.mkdir();
        }

        File file = new File(image_path_default + imageFile.getFilename(imageFile.getId()).replaceFirst("/",pathSeparator));
        String imageFormat = newImageFile.getMimeType().substring(newImageFile.getMimeType().indexOf("/")+1);
//        if(!newImageFile.getMimeType().equals("image/jpeg")){
//            imageFormat = newImageFile.getMimeType().substring(newImageFile.getMimeType().indexOf("/")+1);
//        }

            // -------- Saving the Image in Local Disk -------------------------------- //
            try {
                BufferedImage img = ImageIO.read(new ByteArrayInputStream(newImageFile.getFile()));
                ImageIO.write(img, imageFormat, byteArrayOutputStream);
                imageFile.getFilename(imageFile.getId()).substring(imageFile.getFilename(imageFile.getId()).lastIndexOf(".")+1);
                ImageIO.write(img,imageFormat , file);
                base64String = Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray());
            }catch (Exception e){
                throw new ResourceNotFoundException(messageSource.getMessage(Constants.ErrorMessageCodes.IMAGE_UPLOAD_ERROR_CODE ,null, Locale.ENGLISH),messageSource.getMessage(Constants.ErrorMessageCodes.IMAGE_UPLOAD_ERROR_MESSAGE, null, Locale.ENGLISH));
            }

        return newImageFile;
    }

    @Override
    public String getImagePath(Long id){
        ImageFile imageFile = imageFileDao.find(id);
        if(imageFile != null){
            return imageFile.getFilename(id);
        } else{
            return "";
        }
    }

}
