package kln.se.agri.ai.authorization.model;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@NamedQueries({
        @NamedQuery(
                name = "Role.getRoleByRoleName",
                query = "SELECT rl FROM Role rl where rl.name = :name "
        )
})

@Entity
@Data
@Table(name = "role")
public class Role extends AbstractBaseEntity {

    public static final String GET_ROLE_BY_ROLE_NAME = "Role.getRoleByRoleName";

    @Column(name = "name")
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "permission_role", joinColumns = {
            @JoinColumn(name = "role_id", referencedColumnName = "id")}, inverseJoinColumns = {
            @JoinColumn(name = "permission_id", referencedColumnName = "id")})
    private List<Permission> permissions;

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }


}
