package kln.se.agri.ai.dashboard.dto;

import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String branchId;
    private String branchName;
    private String address;
    private String phoneNumber;
    private String userName;
    private String password;
    private String email;
    private String roles;
    private Long pictureId;
    private Long createdUserId;
    private Long editedUserId;
    private String imageName;
    private String imageFileName;
    private byte[] thumbnail;
    private byte[] imageFile;
}
