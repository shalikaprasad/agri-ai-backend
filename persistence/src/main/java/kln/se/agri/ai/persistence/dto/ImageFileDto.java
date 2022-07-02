package kln.se.agri.ai.persistence.dto;

import lombok.Data;

@Data
public class ImageFileDto {
    private String fileName;
    private String pictureName;
    private String description;
    private Long artifactID;
    private String artifactType;
    private Long createdUserId;
}
