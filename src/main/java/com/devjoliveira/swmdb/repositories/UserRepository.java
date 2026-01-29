package com.devjoliveira.swmdb.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.devjoliveira.swmdb.entities.User;

public interface UserRepository extends MongoRepository<User, String> {

}
