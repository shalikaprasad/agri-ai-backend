package kln.se.agri.ai.dashboard.dto;

import lombok.Data;

@Data
public class CropDto {
    private Long id;
    private String cropName;
    private String cropType;
    private String province;
    private String district;
    private String village;
    private Float cropPrice;
    private Float landSize;
    private Float pressure;
    private Float humidity;
    private Float rainfall;
    private Float temperature;
    private String soilStatus;
}
