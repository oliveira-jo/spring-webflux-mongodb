package com.devjoliveira.swmdb.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devjoliveira.swmdb.dto.UserDTO;
import com.devjoliveira.swmdb.entities.User;
import com.devjoliveira.swmdb.repositories.UserRepository;
import com.devjoliveira.swmdb.services.exceptions.ResourceNotFoundException;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	public Flux<UserDTO> findAll() {
		return repository.findAll().map(x -> new UserDTO(x));
	}

	public Mono<UserDTO> findById(String id) {
		return repository.findById(id).map(x -> new UserDTO(x))
				.switchIfEmpty(Mono.error(new ResourceNotFoundException("Recurso não encontrado")));
	}

	public Mono<UserDTO> insert(UserDTO dto) {
		User entity = new User();
		copyDtoToEntity(dto, entity);
		Mono<UserDTO> result = repository.save(entity).map(user -> new UserDTO(user));
		return result;
	}

	public Mono<UserDTO> update(String id, UserDTO dto) {
		return repository.findById(id)
				.flatMap(existingUser -> {
					existingUser.setName(dto.getName());
					existingUser.setEmail(dto.getEmail());
					return repository.save(existingUser);
				}).map(user -> new UserDTO(user))
				.switchIfEmpty(Mono.error(new ResourceNotFoundException("Recurso não encontrado")));
	}

	public Mono<Void> delete(String id) {
		return repository.findById(id)
				.switchIfEmpty(Mono.error(new ResourceNotFoundException("Recurso não encontrado")))
				.flatMap(existingUser -> repository.delete(existingUser));
	}

	private void copyDtoToEntity(UserDTO dto, User entity) {
		entity.setName(dto.getName());
		entity.setEmail(dto.getEmail());
	}
}
