package com.devjoliveira.swmdb.services;

import java.time.Instant;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devjoliveira.swmdb.dto.PostDTO;
import com.devjoliveira.swmdb.repositories.PostRepository;
import com.devjoliveira.swmdb.services.exceptions.ResourceNotFoundException;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PostService {

	@Autowired
	private PostRepository repository;

	public Mono<PostDTO> findById(String id) {
		return repository.findById(id).map(x -> new PostDTO(x))
				.switchIfEmpty(Mono.error(new ResourceNotFoundException("Recurso não encontrado")));
	}

	public Flux<PostDTO> findByTitle(String text) {
		return repository.searchTitle(text).map(postFound -> new PostDTO(postFound));
	}

	public Flux<PostDTO> fullSearch(String text, Instant minDate, Instant maxDate) {
		maxDate = maxDate.plusSeconds(86400); // 24 * 60 * 60
		Flux<PostDTO> result = repository.fullSearch(text, minDate,
				maxDate).map(x -> new PostDTO(x));
		return result;
	}

	public Flux<PostDTO> findByUser(String id) {
		return repository.findByUser(new ObjectId(id))
				.map(post -> new PostDTO(post));
	}

}
