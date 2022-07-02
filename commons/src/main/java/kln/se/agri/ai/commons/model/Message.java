package kln.se.agri.ai.commons.model;

import lombok.Data;
import javax.persistence.*;

@NamedQueries({
        @NamedQuery(
                name = Message.GET_MESSAGE_LIST,
                query = "SELECT m FROM Message m where m.fromId = :fromId and m.fromId = :toId"
        )
})

@Entity
@Data
@Table(name = "message")
public class Message extends AbstractBaseEntity {

    public static final String GET_MESSAGE_LIST = "Message.getMessageList";

    @Column(name = "time")
    private String time;

    @Column(name = "message")
    private String message;

    @Column(name = "from_id")
    private String fromId;

    @Column(name = "to_id")
    private String toId;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

}
