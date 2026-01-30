package com.devjoliveira.swmdb.services;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devjoliveira.swmdb.dto.PostDTO;
import com.devjoliveira.swmdb.entities.Post;
import com.devjoliveira.swmdb.repositories.PostRepository;
import com.devjoliveira.swmdb.services.exceptions.ResourceNotFoundException;

import reactor.core.publisher.Mono;

@Service
public class PostService {

	@Autowired
	private PostRepository repository;

	public Mono<PostDTO> findById(String id) {
		return repository.findById(id).map(x -> new PostDTO(x))
				.switchIfEmpty(Mono.error(new ResourceNotFoundException("Recurso não encontrado")));
	}

	// public List<PostDTO> findByTitle(String text) {
	// List<PostDTO> result = repository.searchTitle(text).stream().map(x -> new
	// PostDTO(x)).toList();
	// return result;
	// }

	// public List<PostDTO> fullSearch(String text, Instant minDate, Instant
	// maxDate) {
	// maxDate = maxDate.plusSeconds(86400); // 24 * 60 * 60
	// List<PostDTO> result = repository.fullSearch(text, minDate,
	// maxDate).stream().map(x -> new PostDTO(x)).toList();
	// return result;
	// }
}
