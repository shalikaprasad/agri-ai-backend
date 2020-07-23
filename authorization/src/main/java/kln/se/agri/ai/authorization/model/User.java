package kln.se.agri.ai.authorization.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@NamedQueries({
        @NamedQuery(
                name = "User.getAllUsers",
                query = "SELECT us FROM User us where us.is_deleted = false "
        )
})

@NamedNativeQueries({
        @NamedNativeQuery(
                name = User.GET_USER_BY_USER_NAME,
                query = "select * from user where user_name = :userName",
                resultClass = User.class
        )
})


@Entity
@Data
@Table(name = "user")
public class User extends AbstractBaseEntity {

    public static final String GET_USER_BY_USER_NAME = "User.getUserByUserName";
    public static final String GET_ALL_USERS = "User.getAllUsers";

    public User() {
    }

    public User(User user) {
        this.userName = user.getUserName();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.enabled = user.isEnabled();
        this.accountNonExpired = user.isAccountNonExpired();
        this.credentialsNonExpired = user.isCredentialsNonExpired();
        this.accountNonLocked = user.isAccountNonLocked();
        this.roles = user.getRoles();
    }

    @Column(name = "branch_id")
    private String branchId;

    @Column(name = "branch_name")
    private String branchName;

    @Column(name = "address")
    private String address;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "enabled")
    private boolean enabled;

    @Column(name = "account_non_expired")
    private boolean accountNonExpired;

    @Column(name = "credentials_non_expired")
    private boolean credentialsNonExpired;

    @Column(name = "account_non_locked")
    private boolean accountNonLocked;

    @Column(name = "is_deleted")
    private Boolean is_deleted;

    @Column(name = "picture_id")
    private Long pictureId;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "role_user", joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "role_id", referencedColumnName = "id")})

    private List<Role> roles;

    @Transient
    @Lob
    private byte[] thumbnail;

    @Transient
    private String imageName;

    @Transient
    private byte[] imageFile;

    @Transient
    private String imageFileName;
}
