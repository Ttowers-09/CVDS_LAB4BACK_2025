package com.cvds.eci.laboratoryreservations.app_core.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cvds.eci.laboratoryreservations.app_core.model.Laboratory;

public interface LaboratoryRepository extends MongoRepository<Laboratory,String>{
    Laboratory findByName(String name);
}
