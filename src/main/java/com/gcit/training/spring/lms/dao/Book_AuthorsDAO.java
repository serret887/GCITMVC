package com.gcit.training.spring.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.gcit.training.spring.lms.entity.Author;
import com.gcit.training.spring.lms.entity.Book;
import com.gcit.training.spring.lms.entity.Book_Authors;

public class Book_AuthorsDAO extends BaseDAO<Book_Authors> implements ResultSetExtractor<List<Book_Authors>> {
	private static final String DELETE_AUTHORS_OF_BOOKS = "delete from tbl_book_authors where bookId = ? ";
	
	private static final String DELETE_BOOKS_OF_AUTHOR = "delete from tbl_book_authors where authorId = ? ";
	
	private static final String DELETE = "delete from tbl_book_authors where authorId = ? and bookId = ?";
	private static final String SELECT = "select * from tbl_book_authors";
	private static final String SELECT_BY_AUTHOR_ID = "select * from tbl_book_authors where authorId = ?";
	private static final String SELECT_BY_BOOK_ID = "select * from tbl_book_authors where bookId = ?";
	private static final String SELECT_IF_EXIST = "select * from tbl_book_authors where bookId  = ? and authorId = ? ";

	private static final String INSERT_BOOK_AUTHOR = "insert into tbl_book_authors (authorId,bookId) values( ? , ?)";
	@Autowired
	private JdbcTemplate template;

	/*
	 * return all teh books of an specific author
	 */
	public List<Book_Authors> getByAuthorId(int authorId) {
		return template.query(SELECT_BY_AUTHOR_ID, new Object[] { authorId }, this);
	}

	/*
	 * return all teh author of an specific book
	 */
	public List<Book_Authors> getByBookId(int bookId) {
		return template.query(SELECT_BY_BOOK_ID, new Object[] { bookId }, this);
	}

	public void add(Book_Authors item) {
		template.update(INSERT_BOOK_AUTHOR, new Object[] { item.getAuthorId(), item.getBookId() });
	}

	@Override
	public List<Book_Authors> extractData(ResultSet rs) {
		List<Book_Authors> book_authors = new ArrayList<Book_Authors>();

		try {
			while (rs.next()) {
				Book_Authors book_author = new Book_Authors();
				book_author.setAuthorId(rs.getInt("authorId"));
				book_author.setBookId(rs.getInt("bookId"));
				book_authors.add(book_author);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return book_authors;
	}

	public void updateBooksofAuthor(Author author, int[] bookIds) {
		deleteBooksOfAuthor(author.getAuthorId());
		for (int bookId : bookIds) {
			System.out.println(author.getAuthorId() + " " + bookId);
			Book_Authors bookAuthor = new Book_Authors();
			bookAuthor.setAuthorId(author.getAuthorId());
			bookAuthor.setBookId(bookId);
			add(bookAuthor);
		}
	}

	public void deleteBooksOfAuthor(int id) {
		template.update(DELETE_BOOKS_OF_AUTHOR, new Object[] { id });

	}

	public void updateAuthorsofBook(int[] authorsIds, Book book) {
		deleteAuthorsOfBooks(book.getBookId());
		for (int authorId : authorsIds) {
			Book_Authors bookAuthor = new Book_Authors();
			bookAuthor.setAuthorId(authorId);
			bookAuthor.setBookId(book.getBookId());
			add(bookAuthor);
		}

	}

	public void deleteAuthorsOfBooks(int id) {
		template.update(DELETE_AUTHORS_OF_BOOKS, new Object[] { id });

	}

	public void delete(Book_Authors item) {
		template.update(DELETE, new Object[] { item.getAuthorId(), item.getBookId() });

	}

	public List<Book_Authors> readAll(int pageNo) {
		return template.query(SELECT, this);
	}

	public List<Book_Authors> readFirstLevel(int pageNo) {
		return readAll(pageNo);
	}

	/*
	 * this method return me the bookAuthor only if it exist else return null
	 */
	public Book_Authors exist(Book_Authors ba) {
		List<Book_Authors> newba = template.query(SELECT_IF_EXIST, new Object[] { ba.getBookId(), ba.getAuthorId() },
				this);
		if (newba.isEmpty())
			return null;
		return newba.get(0);
	}

	public void addAuthorsForBook(int bookId, int[] authorIds) {
		for (int id : authorIds) {
			template.update(INSERT_BOOK_AUTHOR, id, bookId);
		}
	}

}
