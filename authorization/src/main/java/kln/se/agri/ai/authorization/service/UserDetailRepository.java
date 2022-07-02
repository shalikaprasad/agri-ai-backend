package kln.se.agri.ai.authorization.service;


import kln.se.agri.ai.commons.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDetailRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUserName(String s);
}
