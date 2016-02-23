package com.gcit.training.spring.lms.entity;

import java.io.Serializable;


public class Book_Authors implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1347406787856278260L;
	private int bookId,authorId;

	public int getBookId() {
		return bookId;
	}

	public int getAuthorId() {
		return authorId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}
	
}
