package com.alex.movies.exception;

public class MovieCollectionException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MovieCollectionException(String message) {
		super(message);
	}

	public static String NotFoundException(String id) {
		return "Movie with " + id + " not found";
	}

	public static String TitleAlreadyExists() {
		return "Movie with given title already exists";
	}
}
