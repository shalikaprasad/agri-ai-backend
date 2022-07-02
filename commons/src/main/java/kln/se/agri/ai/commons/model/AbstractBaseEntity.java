package kln.se.agri.ai.commons.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Prasad on 12/15/19.
 */

@MappedSuperclass
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class AbstractBaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * A unique id for the entity.
     */
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(name = "created_UserId")
    private Long createdUserId;

    @Column(name = "edited_UserId")
    private Long editedUserId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEditedUserId() {
        return editedUserId;
    }

    public void setEditedUserId(Long editedUserId) {
        this.editedUserId = editedUserId;
    }

    public Long getCreatedUserId() {
        return createdUserId;
    }

    public void setCreatedUserId(Long createdUserId) {
        this.createdUserId = createdUserId;
    }

    /**
     * Generic hash (Override if you need more unique)
     */
    @Override
    public int hashCode() {
        return (id != null ? id.hashCode() : -1);
    }

    /**
     * Equals method based ONLY on id.
     */
    @Override
    public boolean equals(Object obj) {
        return obj != null && (obj instanceof AbstractBaseEntity) && ((AbstractBaseEntity) obj).id.equals(this.id);
    }

    /**
     * @returns "entity." + class + "[ id ]"
     */
    @Override
    public String toString() {
        return "entity." + this.getClass() + "[ id=" + id + " ] ";
    }

}
