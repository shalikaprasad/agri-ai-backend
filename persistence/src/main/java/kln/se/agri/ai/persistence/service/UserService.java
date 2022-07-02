package kln.se.agri.ai.persistence.service;

import kln.se.agri.ai.commons.model.Farmer;
import kln.se.agri.ai.commons.model.Role;
import kln.se.agri.ai.commons.model.User;

import java.util.List;

/**
 * Created by Prasad on 12/15/19.
 */
public interface UserService {

    User getUserByUsername(String name);

    List<Farmer> getAllFarmers();

    Long deleteFarmer(Long id);

    Farmer getFarmer(Long id);

    Farmer createFarmer(Farmer farmer, Long id);

    List<User> getAllUsers();

    Long deleteUser(Long valueOf);

    User createUser(User user1);

    Role getRoleByRoleName(String roleName);
}

