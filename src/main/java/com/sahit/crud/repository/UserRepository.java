package com.sahit.crud.repository;

/**
 * Repository class
 */
import com.sahit.crud.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {

    void delete(User user);

    List<User> findAll();

    User findById(int id);

    User save(User user);
}
