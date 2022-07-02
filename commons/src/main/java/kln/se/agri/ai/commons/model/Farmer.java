package kln.se.agri.ai.commons.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@NamedQueries({
        @NamedQuery(
                name = "Farmer.getFarmerByIdNumber",
                query = "SELECT fa FROM Farmer fa where fa.idNumber =:idNumber"
        ),
        @NamedQuery(
                name = "Farmer.getAllFarmers",
                query = "SELECT fa FROM Farmer fa where fa.is_deleted = false "
        )
})

@NamedNativeQueries({
        @NamedNativeQuery(
                name = Farmer.GET_FARMER_BY_USER_NAME,
                query = "select * from farmer where username = :username",
                resultClass = Farmer.class
        )
})

@Entity
@Data
@Table(name = "farmer")
public class Farmer extends AbstractBaseEntity {

    public static final String GET_FARMER_BY_USER_NAME = "Farmer.getFarmerByUserName";
    public static final String GET_FARMER_BY_ID_NUMBER = "Farmer.getFarmerByIdNumber";
    public static final String GET_ALL_FARMERS = "Farmer.getAllFarmers";

    @Column(name = "id_number")
    @NotNull
    private String idNumber;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "district")
    private String district;

    @Column(name = "village")
    private String village;

    @Column(name = "phoneNumber")
    private Integer phoneNumber;

    @Column(name = "is_deleted")
    private Boolean is_deleted;

    @JsonIgnore
    @OneToMany(mappedBy = "farmer")
    private Set<Project> projects;

}
