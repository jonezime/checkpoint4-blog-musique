package com.blogmusique.Blog.Musique.repository;

import com.blogmusique.Blog.Musique.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmailAndPassword (String email, String password);

    List<User> findAllByRoleEquals(String role);

}
