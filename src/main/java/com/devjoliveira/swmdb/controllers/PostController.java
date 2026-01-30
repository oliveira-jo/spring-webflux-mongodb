package com.devjoliveira.swmdb.controllers;

import java.io.UnsupportedEncodingException;
import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import java.text.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devjoliveira.swmdb.controllers.util.URL;
import com.devjoliveira.swmdb.dto.PostDTO;
import com.devjoliveira.swmdb.services.PostService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/posts")
public class PostController {

	@Autowired
	private PostService service;

	@GetMapping(value = "/{id}")
	public Mono<ResponseEntity<PostDTO>> findById(@PathVariable String id) {
		return service.findById(id).map(postDTO -> ResponseEntity.ok().body(postDTO));
	}

	@GetMapping(value = "/titlesearch")
	public ResponseEntity<Flux<PostDTO>> findByTitle(@RequestParam(value = "text", defaultValue = "") String text)
			throws UnsupportedEncodingException {
		text = URL.decodeParam(text);
		Flux<PostDTO> list = service.findByTitle(text);
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/fullsearch")
	public ResponseEntity<Flux<PostDTO>> fullSearch(
			@RequestParam(value = "text", defaultValue = "") String text,
			@RequestParam(value = "minDate", defaultValue = "") String minDate,
			@RequestParam(value = "maxDate", defaultValue = "") String maxDate)
			throws UnsupportedEncodingException, ParseException {

		text = URL.decodeParam(text);
		Instant min = URL.convertDate(minDate, Instant.EPOCH);
		Instant max = URL.convertDate(maxDate, Instant.now());

		Flux<PostDTO> list = service.fullSearch(text, min, max);
		return ResponseEntity.ok().body(list);
	}
}
