package kln.se.agri.ai.persistence.dto;

import kln.se.agri.ai.commons.model.Crop;
import kln.se.agri.ai.commons.model.Farmer;
import lombok.Data;

@Data
public class ProjectDto {
    private Long id;
    private Farmer farmer;
    private Crop crop;
    private String farmerName;
    private String farmingType;
    private String cropName;
    private String district;
    private String village;
    private float landSize;
    private float pressure;
    private float humidity;
    private float rainfall;
    private float temperature;
    private String soilStatus;
    private String startDate;
    private String updateDate;
    private float income;
    private float expensive;
    private boolean isFail;
    private boolean isExpired;
    private String reasonFail;
    private String description;
}
