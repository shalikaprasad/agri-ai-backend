package kln.se.agri.ai.user.dao.hibernate;

import kln.se.agri.ai.commons.model.User;
import kln.se.agri.ai.persistence.dao.OfficerDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Prasad on 12/15/19.
 */

@Repository("officerDao")
@Transactional
public class OfficerDaoImpl extends CRUDDaoImpl<User> implements OfficerDao {

    private final static Logger logger = LoggerFactory.getLogger(OfficerDaoImpl.class);

}
