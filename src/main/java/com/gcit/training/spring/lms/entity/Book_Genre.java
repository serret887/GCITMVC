package com.gcit.training.spring.lms.entity;

import java.io.Serializable;


public class Book_Genre implements Serializable {
/**
	 * 
	 */
	private static final long serialVersionUID = -7281088347119654618L;
private int genre_id;
private int bookId;

public int getGenre_id() {
	return genre_id;
}
public int getBookId() {
	return bookId;
}
public void setGenre_id(int genre_id) {
	this.genre_id = genre_id;
}
public void setBookId(int genre_name) {
	this.bookId = genre_name;
}

}
