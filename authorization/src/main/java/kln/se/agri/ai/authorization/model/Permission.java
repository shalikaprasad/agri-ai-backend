package kln.se.agri.ai.authorization.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "permission")
public class Permission extends AbstractBaseEntity {

    @Column(name = "category")
    private String category;

    @Column(name = "name")
    private String name;

}
