package com.gcit.training.spring.lms.entity;


import java.io.Serializable;
import java.util.List;

public class Genre implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -74017092512017613L;

	private int genreId;
	
	private String genreName;
	
	private List<Book> books;

	/**
	 * @return the genreId
	 */
	public int getGenreId() {
		return genreId;
	}

	/**
	 * @param genreId the genreId to set
	 */
	public void setGenreId(int genreId) {
		this.genreId = genreId;
	}

	/**
	 * @return the genreName
	 */
	public String getGenreName() {
		return genreName;
	}

	/**
	 * @param genreName the genreName to set
	 */
	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}

	/**
	 * @return the books
	 */
	public List<Book> getBooks() {
		return books;
	}

	/**
	 * @param books the books to set
	 */
	public void setBooks(List<Book> books) {
		this.books = books;
	}
	
}
