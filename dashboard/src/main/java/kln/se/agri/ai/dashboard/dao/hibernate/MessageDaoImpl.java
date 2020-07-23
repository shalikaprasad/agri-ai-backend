package kln.se.agri.ai.dashboard.dao.hibernate;

import kln.se.agri.ai.commons.model.Message;
import kln.se.agri.ai.persistence.dao.MessageDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Prasad on 12/15/19.
 */

@Repository("messageDao")
@Transactional
public class MessageDaoImpl extends CRUDDaoImpl<Message> implements MessageDao {

    private final static Logger logger = LoggerFactory.getLogger(MessageDaoImpl.class);

    @Override
    public List<Message> getMessageListForUser(String fromId, String toId) {
        return (List<Message>) entityManager.createNamedQuery(Message.GET_MESSAGE_LIST)
                .setParameter("fromId", fromId)
                .setParameter("toId", toId)
                .getResultList();
    }


}
