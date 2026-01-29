package com.devjoliveira.swmdb.repositories;

import java.time.Instant;
import java.util.List;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.devjoliveira.swmdb.entities.Post;

public interface PostRepository extends ReactiveMongoRepository<Post, String> {

	// here we user flux
	@Query("{ 'title': { $regex: ?0, $options: 'i' } }")
	List<Post> searchTitle(String text);

	// here we user flux
	List<Post> findByTitleContainingIgnoreCase(String text);

	// here we user flux
	@Query("{ $and: [ { date: {$gte: ?1} }, { date: { $lte: ?2} } , { $or: [ { 'title': { $regex: ?0, $options: 'i' } }, { 'body': { $regex: ?0, $options: 'i' } }, { 'comments.text': { $regex: ?0, $options: 'i' } } ] } ] }")
	List<Post> fullSearch(String text, Instant minDate, Instant maxDate);
}
