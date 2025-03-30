package com.cvds.eci.laboratoryreservations.app_core.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cvds.eci.laboratoryreservations.app_core.model.User;

public interface UserRepository extends MongoRepository<User,String> {
    User findByName(String name);

    User findByEmail(String email);
}
