package kln.se.agri.ai.persistence.dao;

import kln.se.agri.ai.commons.model.Role;

/**
 * Created by Prasad on 12/15/19.
 */

public interface RoleDao extends CRUDDao<Role> {

    Role findByRoleNme(String name);

}
