package com.blogmusique.oandzim.repository;

import com.blogmusique.oandzim.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmailAndPassword (String email, String password);

    List<User> findAllByRoleEquals(String role);

}
