package kln.se.agri.ai.dashboard.model;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;

/**
 * Created by Prasad on 12/15/19.
 */

@NamedQueries({
        @NamedQuery(
                name = "Crop.getCropByName",
                query = "SELECT c FROM Crop c where c.cropName =:cropName and c.isDeleted = false"
        ),
        @NamedQuery(
                name = "Crop.getAllCrops",
                query = "SELECT c FROM Crop c where c.isDeleted = false"
        ),
        @NamedQuery(
                name = "Crop.getAllCropNames",
                query = "SELECT distinct c.cropName FROM Crop c where c.isDeleted = false"
        )
})

@Entity
@Data
@Table(name="crop")
public class Crop extends AbstractBaseEntity {

    public static final String GET_CROP_BY_NAME = "Crop.getCropByName";
    public static final String GET_ALL_CROPS = "Crop.getAllCrops";
    public static final String GET_ALL_CROP_NAME = "Crop.getAllCropNames";

    public Crop() {
    }

    @Column(name = "crop_name")
    @NotNull
    private String cropName;

    @Column(name = "crop_type")
    @NotNull
    private String cropType;

    @Column(name = "district")
    private String district;

    @Column(name = "crop_price")
    private Float cropPrice;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

}
