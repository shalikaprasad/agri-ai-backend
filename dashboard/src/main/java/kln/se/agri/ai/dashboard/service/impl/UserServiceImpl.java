package kln.se.agri.ai.dashboard.service.impl;

import kln.se.agri.ai.commons.model.Farmer;
import kln.se.agri.ai.commons.model.Role;
import kln.se.agri.ai.commons.model.User;
import kln.se.agri.ai.persistence.dao.FarmerDao;
import kln.se.agri.ai.persistence.dao.RoleDao;
import kln.se.agri.ai.persistence.dao.UserDao;
import kln.se.agri.ai.persistence.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    public UserDao userDao;

    @Autowired
    public FarmerDao farmerDao;

    @Autowired
    public RoleDao roleDao;


    @Override
    public User getUserByUsername(String name) throws UsernameNotFoundException {
        return userDao.findByUsername(name);
    }

    @Override
    public List<Farmer> getAllFarmers(){
        return farmerDao.getFarmerList();
    }

    @Override
    public Long deleteFarmer(Long id){
        try {
            Farmer farmer = farmerDao.find(id);
            farmer.setIs_deleted(true);
            farmerDao.update(farmer);
            return id;
        }
        catch (Exception e){
            System.out.println("Cannot Find Farmer");
            return null;
        }
    }

    @Override
    public Farmer getFarmer(Long id){
        try {
            Farmer farmer = farmerDao.find(id);
            return farmer;
        }
        catch (Exception e){
            System.out.println("Cannot Find Farmer");
            return null;
        }
    }

    @Override
    public Farmer createFarmer(Farmer farmer, Long userId) {
        try {
            Farmer farmer1 = farmerDao.findFarmerByIdNumber(farmer.getIdNumber());
            long id = farmer1.getId();
            farmer.setId(id);
            farmer.setEditedUserId(userId);
            return farmerDao.update(farmer);
        }catch (Exception e){
            farmer.setId(null);
            farmer.setCreatedUserId(userId);
            return farmerDao.create(farmer);
        }
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getUserList();
    }

    @Override
    public Long deleteUser(Long id) {
        try {
            User user = userDao.find(id);
            user.setIs_deleted(true);
            userDao.update(user);
            return id;
        }
        catch (Exception e){
            System.out.println("Cannot Find User");
            return null;
        }
    }

    @Override
    public User createUser(User user) {
        user.setEnabled(true);
        user.setAccountNonExpired(true);
        user.setCredentialsNonExpired(true);
        user.setAccountNonLocked(true);
        try {
            User user1 = userDao.findByUsername(user.getUserName());
            long id = user1.getId();
            user.setId(id);
            return userDao.update(user);
        }catch (Exception e){
            user.setId(null);
            return userDao.create(user);
        }
    }

    @Override
    public Role getRoleByRoleName(String roleName) {
        return roleDao.findByRoleNme(roleName);
    }


}
