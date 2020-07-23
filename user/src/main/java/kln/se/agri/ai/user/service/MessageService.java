package kln.se.agri.ai.user.service;

import kln.se.agri.ai.commons.model.Message;

import java.util.List;

/**
 * Created by Prasad on 04/10/20.
 */
public interface MessageService {

    List<Message> getMessageList(String fromId, String toId);

    Message saveMessage(Message message);
}

