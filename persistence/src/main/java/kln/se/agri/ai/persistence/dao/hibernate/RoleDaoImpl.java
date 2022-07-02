package kln.se.agri.ai.persistence.dao.hibernate;

import kln.se.agri.ai.commons.model.Role;
import kln.se.agri.ai.persistence.dao.RoleDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Prasad on 12/15/19.
 */

@Repository("roleDao")
@Transactional
public class RoleDaoImpl extends CRUDDaoImpl<Role> implements RoleDao {

    private final static Logger logger = LoggerFactory.getLogger(RoleDaoImpl.class);

    @Override
    public Role findByRoleNme(String name) {
        return (Role) entityManager.createNamedQuery(Role.GET_ROLE_BY_ROLE_NAME)
                .setParameter("name", name)
                .getSingleResult();
    }




}
