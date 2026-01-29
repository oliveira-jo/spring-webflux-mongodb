package com.devjoliveira.swmdb.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.devjoliveira.swmdb.entities.User;

public interface UserRepository extends ReactiveMongoRepository<User, String> {

}
