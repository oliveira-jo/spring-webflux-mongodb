package com.devjoliveira.workshopmongo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.devjoliveira.workshopmongo.entities.User;

public interface UserRepository extends MongoRepository<User, String> {

}
