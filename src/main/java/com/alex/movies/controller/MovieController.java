package com.alex.movies.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alex.movies.exception.MovieCollectionException;
import com.alex.movies.model.Movie;
import com.alex.movies.services.MovieService;

@RestController
public class MovieController {

	@Autowired
	private MovieService movieService;

	@PostMapping("/movie")
	public ResponseEntity<String> createMovie(@RequestBody Movie movie) {
		try {
			movieService.createMovie(movie);
			return new ResponseEntity("Successfully added movie " + movie.getTitle(), HttpStatus.OK);
		} catch (ConstraintViolationException e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
		} catch (MovieCollectionException e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
		}
	}

	@GetMapping("/movie")
	public ResponseEntity<String> getAllMovies() {
		List<Movie> movies = movieService.getAllMovies();
		return new ResponseEntity(movies, movies.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);

	}

	@DeleteMapping("/movie/{id}")
	public ResponseEntity deleteMovieById(@PathVariable("id") String id) {
		try {
			movieService.deleteMovieById(id);
			return new ResponseEntity("Successfully deleted movie with id " + id, HttpStatus.OK);
		} catch (MovieCollectionException e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/movie/{id}")
	public ResponseEntity updateById(@PathVariable("id") String id, @RequestBody Movie newMovie) {
		try {
			movieService.updateMovie(id, newMovie);
			return new ResponseEntity("Updated movie with id " + id + "", HttpStatus.OK);
		} catch (ConstraintViolationException e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
		} catch (MovieCollectionException e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

}
