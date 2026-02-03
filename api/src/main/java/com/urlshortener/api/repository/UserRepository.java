package com.urlshortener.api.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

import com.urlshortener.api.model.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findByUsername(String username);

    User findById(long id);
    
}
