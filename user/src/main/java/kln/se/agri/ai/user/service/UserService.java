package kln.se.agri.ai.user.service;

import kln.se.agri.ai.dashboard.model.Farmer;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created by Prasad on 12/15/19.
 */
public interface UserService {

    User getUserByUsername(String name) throws UsernameNotFoundException;

    List<Farmer> getAllFarmers();

    Long deleteFarmer(Long id);

    Farmer getFarmer(Long id);

    Farmer createFarmer(Farmer farmer, Long id);

    List<User> getAllUsers();

    Long deleteUser(Long valueOf);

    User createUser(User user1);

    Role getRoleByRoleName(String roleName);
}

