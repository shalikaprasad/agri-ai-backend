package kln.se.agri.ai.dashboard.service.impl;

import kln.se.agri.ai.commons.model.Message;
import kln.se.agri.ai.persistence.dao.MessageDao;
import kln.se.agri.ai.persistence.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("messageService")
public class MessageServiceImpl implements MessageService {

    @Autowired
    public MessageDao messageDao;

    @Override
    public List<Message> getMessageList(String fromId, String toId){
        return messageDao.getMessageListForUser(fromId, toId);
    }

    @Override
    public Message saveMessage(Message message){
        return messageDao.create(message);
    }
}
