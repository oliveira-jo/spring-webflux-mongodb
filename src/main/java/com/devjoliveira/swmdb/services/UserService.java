package com.devjoliveira.swmdb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devjoliveira.swmdb.dto.PostDTO;
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

	// @Transactional(readOnly = true)
	// public List<PostDTO> findPosts(String id) {
	// User user = repository.findById(id).orElseThrow(() -> new
	// ResourceNotFoundException("Recurso não encontrado"));
	// List<PostDTO> result = user.getPosts().stream().map(x -> new
	// PostDTO(x)).toList();
	// return result;
	// }

	// @Transactional
	// public UserDTO insert(UserDTO dto) {
	// User entity = new User();
	// copyDtoToEntity(dto, entity);
	// entity = repository.save(entity);
	// return new UserDTO(entity);
	// }

	// @Transactional
	// public UserDTO update(String id, UserDTO dto) {
	// User entity = repository.findById(id)
	// .orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
	// copyDtoToEntity(dto, entity);
	// entity = repository.save(entity);
	// return new UserDTO(entity);
	// }

	// @Transactional
	// public void delete(String id) {
	// User entity = repository.findById(id)
	// .orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
	// repository.delete(entity);
	// }

	// private void copyDtoToEntity(UserDTO dto, User entity) {
	// entity.setName(dto.getName());
	// entity.setEmail(dto.getEmail());
	// }
}
