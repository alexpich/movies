package com.alex.movies.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.alex.movies.model.Movie;

public interface MovieRepository extends MongoRepository<Movie, String> {
	@Query("{'title':?0}")
	Optional<Movie> findByTitle(String title);
}
