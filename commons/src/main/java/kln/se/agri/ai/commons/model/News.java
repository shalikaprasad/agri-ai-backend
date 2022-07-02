package kln.se.agri.ai.commons.model;

import com.sun.istack.NotNull;
import lombok.Data;
import javax.persistence.*;

/**
 * Created by Prasad on 12/15/19.
 */

@NamedQueries({

})

@NamedNativeQueries({
        @NamedNativeQuery(
                name = News.GET_NEWS_BY_COUNT,
                query = "SELECT * FROM news ORDER BY id DESC LIMIT :count",
                resultClass = News.class
        )
})

@Entity
@Data
@Table(name="news")
public class News extends AbstractBaseEntity {

    public static final String GET_NEWS_BY_COUNT = "News.getNewsByCount";

    public News() {
    }

    @Column(name = "topic")
    @NotNull
    private String topic;

    @Column(name = "category")
    private String category;

    @Column(name = "description")
    private String description;

    @Column(name = "short_description")
    private String shortDescription;

    @Column(name = "picture_id")
    private Long pictureId;

    @Column(name = "month")
    private String month;

    @Column(name = "date")
    private String date;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

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
