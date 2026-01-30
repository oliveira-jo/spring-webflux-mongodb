package com.devjoliveira.swmdb.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.devjoliveira.swmdb.dto.PostDTO;
import com.devjoliveira.swmdb.dto.UserDTO;
import com.devjoliveira.swmdb.services.UserService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/users")
public class UserController {

	@Autowired
	private UserService service;

	@GetMapping
	public ResponseEntity<Flux<UserDTO>> findAll() {
		Flux<UserDTO> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public Mono<ResponseEntity<UserDTO>> findById(@PathVariable String id) {
		return service.findById(id).map(userDTO -> ResponseEntity.ok().body(userDTO));
	}

	// @GetMapping(value = "/{id}/posts")
	// public ResponseEntity<List<PostDTO>> findPosts(@PathVariable String id) {
	// List<PostDTO> list = service.findPosts(id);
	// return ResponseEntity.ok().body(list);
	// }

	@PostMapping
	public Mono<ResponseEntity<UserDTO>> insert(@RequestBody UserDTO dto, UriComponentsBuilder uriBuilder) {
		return service.insert(dto).map(userCreated -> ResponseEntity
				.created(uriBuilder.path("/users/{id}")
						.buildAndExpand(userCreated.getId()).toUri())
				.body(userCreated));
	}

	// @PutMapping(value = "/{id}")
	// public ResponseEntity<UserDTO> update(@PathVariable String id, @RequestBody
	// UserDTO dto) {
	// dto = service.update(id, dto);
	// return ResponseEntity.ok(dto);
	// }

	// @DeleteMapping(value = "/{id}")
	// public ResponseEntity<Void> delete(@PathVariable String id) {
	// service.delete(id);
	// return ResponseEntity.noContent().build();
	// }
}
