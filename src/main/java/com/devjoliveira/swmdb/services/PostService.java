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

@Service
public class PostService {

	@Autowired
	private PostRepository repository;

	// @Transactional(readOnly = true)
	// public PostDTO findById(String id) {
	// Post post = repository.findById(id).orElseThrow(() -> new
	// ResourceNotFoundException("Recurso não encontrado"));
	// return new PostDTO(post);
	// }

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
