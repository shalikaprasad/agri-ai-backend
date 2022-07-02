package kln.se.agri.ai.commons.model;

import com.sun.istack.NotNull;
import lombok.Data;
import javax.persistence.*;
import java.util.Date;

/**
 * Created by Prasad on 12/15/19.
 */

@NamedQueries({
        @NamedQuery(
                name = Project.GET_ALL_PROJECTS_FOR_FARMER,
                query = "SELECT p FROM Project p where p.farmer.id = :farmerId AND p.isDeleted = false"
        ),
        @NamedQuery(
                name = Project.GET_PROJECTS_WITH_MONTH,
                query = "SELECT p FROM Project p where p.farmer.id = :monthCount AND p.isDeleted = false"
        )
})

@Entity
@Data
@Table(name="project")
public class Project extends AbstractBaseEntity {

    public static final String GET_ALL_PROJECTS_FOR_FARMER = "Crop.getAllProjectsForFarmer";
    public static final String GET_PROJECTS_WITH_MONTH = "Crop.getProjectsWithMonths";
    public static final String GET_PROJECT_COUNTS_FOR_FARMERS = "Crop.getProjectCountForFarmer";
    public static final String GET_CROP_COUNTS = "Crop.getCropCounts";
    public static final String GET_PROFIT_COUNTS = "Crop.getProfitCount";

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "farmer_id")
    private Farmer farmer;

    @Column(name = "farmer_type")
    private String farmingType;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "crop_id")
    private Crop crop;

    @Column(name = "district")
    private String district;

    @Column(name = "village")
    private String village;

    @Column(name = "land_size")
    private int landSize;

    @Column(name = "pressure")
    private int pressure;

    @Column(name = "humidity")
    private int humidity;

    @Column(name = "rainfall")
    private int rainfall;

    @Column(name = "temperature")
    private int temperature;

    @Column(name = "soil_status")
    private String soilStatus;

    @Column(name = "start_dte")
    private Date startDate;

    @Column(name = "update_date")
    private Date updateDate;

    @Column(name = "income")
    private int income;

    @Column(name = "expensive")
    private int expensive;

    @Column(name = "is_fail")
    private boolean isFail;

    @Column(name = "is_expired")
    private boolean isExpired;

    @Column(name = "reason_fail")
    private String reasonFail;

    @Column(name = "description")
    private String description;

    @Column(name = "expire_date")
    private Date expireDate;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

}
