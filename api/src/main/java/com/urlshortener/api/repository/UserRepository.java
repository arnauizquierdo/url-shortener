package com.urlshortener.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.urlshortener.api.model.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findByUsername(String username);

    Optional<User> findById(long id);
    
}
