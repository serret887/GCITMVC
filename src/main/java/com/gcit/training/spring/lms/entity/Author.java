package com.gcit.training.spring.lms.entity;



import java.io.Serializable;
import java.util.List;

public class Author implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3960809010797441309L;

	private int authorId;
	
	private String authorName;
	
	private List<Book> books;

	/**
	 * @return the authorId
	 */
	public int getAuthorId() {
		return authorId;
	}

	/**
	 * @param authorId the authorId to set
	 */
	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	/**
	 * @return the authorName
	 */
	public String getAuthorName() {
		return authorName;
	}

	/**
	 * @param authorName the authorName to set
	 */
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
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
