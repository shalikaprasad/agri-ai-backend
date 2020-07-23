package kln.se.agri.ai.dashboard.dao;

import kln.se.agri.ai.commons.model.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserDao extends CRUDDao<User>{

    User findByUsername(String username);

    List<User> getUserList();

}
