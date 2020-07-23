package kln.se.agri.ai.dashboard.dao;

import kln.se.agri.ai.commons.model.Message;

import java.util.List;

public interface MessageDao extends CRUDDao<Message>{
    List<Message> getMessageListForUser(String fromId, String toId);
}
