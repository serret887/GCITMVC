package com.gcit.training.spring.lms.service.entities;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.gcit.training.spring.lms.dao.Book_AuthorsDAO;
import com.gcit.training.spring.lms.dao.author.AuthorDAO;
import com.gcit.training.spring.lms.entity.Author;

public class AuthorService {

	@Autowired
	AuthorDAO authorDAO;
	@Autowired
	Book_AuthorsDAO bookAuthorDAO;

	// @Autowired
	// BookDAO bookDAO;

	// ////////////////////////////////// AUTHORS///////////////////////////
	public int addAuthor(Author author) {
		return authorDAO.add(author);
	}

	public List<Author> readAuthors(int pageNo) {
		return authorDAO.readAll(pageNo);
	}

	public void editAuthor(Author author, int[] bookIds) {
		
		bookAuthorDAO.updateBooksofAuthor(author, bookIds);
		
		authorDAO.update(author);

	}

	public void deleteAuthor(Author author) {

		bookAuthorDAO.deleteBooksOfAuthor(author.getAuthorId());
		authorDAO.delete(author);
	}

	public Author getAuthorById(int id) {
		return authorDAO.getById(id);
	}
	////////////////////////////////////////////////// BOOKS////////////////////////////

	public List<Author> readFirstInfo(int i) {
		return authorDAO.readFirstLevel(i);
	}
	
	
	
	public List<Author> searchAuthorByName(String searchString, int pageNo) {
		// TODO Auto-generated method stub
		return authorDAO.searchByName(searchString, pageNo);
	}

}