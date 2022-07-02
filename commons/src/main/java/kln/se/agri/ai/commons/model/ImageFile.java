package kln.se.agri.ai.commons.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import kln.se.agri.ai.commons.config.ImageFileSerializer;
import lombok.Data;
import com.sun.istack.NotNull;
import javax.persistence.*;
import java.util.Date;

@NamedQueries({
        @NamedQuery(
                name = ImageFile.GET_IMAGE_BY_NAME,
                query = "SELECT i FROM ImageFile i where i.pictureName like :pictureName"
        ),
        @NamedQuery(
                name = ImageFile.GET_ALL_EXISTING_DISTINCT_IMAGES,
                query = "SELECT i FROM ImageFile i where i.isdeleted=false ORDER BY id DESC"
        ),
        @NamedQuery(
                name = ImageFile.GET_IMAGE_BY_ID,
                query = "SELECT i FROM ImageFile i where i.filename = :picUuid"
        )
})

@JsonSerialize(using = ImageFileSerializer.class)
@Entity
@Table(name = "imagefile")
public class ImageFile extends AbstractBaseEntity  {


        public static final String GET_IMAGE_BY_NAME = "ImageFile.getImageByName";
        public static final String GET_ALL_EXISTING_DISTINCT_IMAGES = "ImageFile.getAllExistingDistinctImages";
        public static final String GET_IMAGE_BY_ID = "ImageFile.getImageById";

        public ImageFile(String filename, String pictureName,byte[] file, String mimeType,String description,long artifactID,String artifactType,  byte[] thumbnail) {
            this.file = file;
            this.filename = filename;
            this.pictureName = pictureName;
            this.mimeType = mimeType;
            this.description=description;
            this.artifactID = artifactID;
            this.artifactType = artifactType;
            this.thumbnail = thumbnail;
        }

        private static final int NUMBER_OF_PICTURES_IN_EACH_FOLDER = 1000;

    public ImageFile(String filename, String pictureName, byte[] bytes, String mimeType, byte[] thumbnail) {
        super();
    }

    /**
         * Returns the pictures folder number as string.
         */
        public static String getPictureFolderNumber(long pictureId) {
            int folderNumber = (int) Math.round((double) pictureId / NUMBER_OF_PICTURES_IN_EACH_FOLDER + 0.5);

            return folderNumber + "/";
        }


        public ImageFile() {
            // Default Constructor
        }

        @Column(name = "picuuid")
        private String filename;

        @Column(name = "original_image")
        private Long originalImage = null;

        @Column(name = "pictureName")
        private String pictureName;

        @Column(name = "description")
        private String description;

        @javax.persistence.Transient
        @Lob
        private byte[] file;

        @javax.persistence.Transient
        @Lob
        private byte[] thumbnail;

        @Column(name = "created_date")
        private Date createdDate;

        private String mimeType;

        @javax.persistence.Transient
        private Long artifactID;

        @Column(name = "is_deleted")
        private boolean isdeleted;

        @javax.persistence.Transient
        private String artifactType;

        public String getFilename(long pictureID) {
            return getPictureFolderNumber(pictureID)+filename+"."+getMimeType().substring(getMimeType().indexOf("/")+1);
        }

        public  Long getId(){
            return id;
        }

        public void setFilename(String filename) {
            this.filename = filename;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public byte[] getFile() {
            return file;
        }

        public void setFile(byte[] file) {
            this.file = file;
        }

        public String getMimeType() {
            return mimeType;
        }

        public void setMimeType(String mimeType) {
            this.mimeType = mimeType;
        }

        public Date getCreatedDate() {
            return createdDate;
        }

        public void setCreatedDate(Date createdDate) {
            this.createdDate = createdDate;
        }

        public byte[] getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(byte[] thumbnail) {
            this.thumbnail = thumbnail;
        }

        public Long getArtifactID() {
            return artifactID;
        }

        public void setArtifactID(Long activityID) {
            this.artifactID = artifactID;
        }

        public boolean isIsdeleted() {
            return isdeleted;
        }

        public void setIsdeleted(boolean isdeleted) {
            this.isdeleted = isdeleted;
        }

        public String getArtifactType() {
            return artifactType;
        }

        public void setArtifactType(String artifactType) {
            this.artifactType = artifactType;
        }

        public String getPictureName() {
            return pictureName;
        }

        public void setPictureName(String pictureName) {
            this.pictureName = pictureName;
        }

        public String getFilename() {
            return filename;
        }

        public Long getOriginalImage() {
            return originalImage;
        }

        public void setOriginalImage(Long originalImage) {
            this.originalImage = originalImage;
        }
}
