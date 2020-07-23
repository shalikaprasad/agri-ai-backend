package kln.se.agri.ai.dashboard.dto;

import lombok.Data;

@Data
public class FarmerDto {
    private String idNumber;
    private String firstName;
    private String lastName;
    private String district;
    private String village;
    private int phoneNumber;
}
