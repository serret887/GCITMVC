package com.gcit.training.spring.lms.entity;


import java.io.Serializable;
import java.util.List;

public class Book implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -149783172540389932L;

	private int bookId;
	
	private String title;
	
	private Publisher publisher;
	
	private List<Author> authors;
	
	private List<Genre> genre;

	/**
	 * @return the bookId
	 */
	public int getBookId() {
		return bookId;
	}

	/**
	 * @param bookId the bookId to set
	 */
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the publisher
	 */
	public Publisher getPublisher() {
		return publisher;
	}

	/**
	 * @param publisher the publisher to set
	 */
	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	/**
	 * @return the authors
	 */
	public List<Author> getAuthors() {
		return authors;
	}

	/**
	 * @param authors the authors to set
	 */
	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

	/**
	 * @return the genre
	 */
	public List<Genre> getGenre() {
		return genre;
	}

	/**
	 * @param genre the genre to set
	 */
	public void setGenre(List<Genre> genre) {
		this.genre = genre;
	}
	
}
