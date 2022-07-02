package kln.se.agri.ai.persistence.dao.hibernate;

import kln.se.agri.ai.commons.model.User;
import kln.se.agri.ai.persistence.dao.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Prasad on 12/15/19.
 */

@Repository("userDao")
@Transactional
public class UserDaoImpl extends CRUDDaoImpl<User> implements UserDao {

    private final static Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    @Override
    public User findByUsername(String userName) {
        return (User) entityManager.createNamedQuery(User.GET_USER_BY_USER_NAME)
                .setParameter("userName", userName)
                .getSingleResult();
    }

    @Override
    public List<User> getUserList() {
        return (List<User>) entityManager.createNamedQuery(User.GET_ALL_USERS)
                .getResultList();
    }



}
