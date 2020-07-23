package kln.se.agri.ai.dashboard.dto;

import lombok.Data;

@Data
public class NewsDto {
    private Long id;
    private String topic;
    private String category;
    private String description;
    private String shortDescription;
    private Long pictureId;
    private String month;
    private String date;
    private Long createdUserId;
    private Long editedUserId;
    private String imageName;
    private String imageFileName;
    private byte[] thumbnail;
    private byte[] imageFile;
    private Boolean isActive;
}
