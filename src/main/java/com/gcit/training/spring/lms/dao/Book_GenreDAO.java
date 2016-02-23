package com.gcit.training.spring.lms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.gcit.training.spring.lms.entity.Book;
import com.gcit.training.spring.lms.entity.Book_Authors;
import com.gcit.training.spring.lms.entity.Book_Genre;
import com.gcit.training.spring.lms.entity.Publisher;

public class Book_GenreDAO extends BaseDAO<Book_Genre> implements ResultSetExtractor<List<Book_Genre>> {

	private static final String SELECT_BY_BOOK_ID = "select * from tbl_book_genres where bookId = ? ";
	private static final String SELECT_BY_GENRE_ID = "select * from tbl_book_genres where genre_id = ? ";
	private static final String DELETE_BY_GENRE_ID = "delete from tbl_book_genres where genre_id = ?";
	private static final String DELETE_BY_BOOK_ID = "delete from tbl_book_genres where bookId = ?";
	private static final String INSERT = "insert into tbl_book_genres (genre_id,bookId) values( ? , ?)";
	private final String SELECT_IF_EXIST = "select * from tbl_book_genres where bookId = ? and genre_id = ?";

	public void add(Book_Genre bauthor) throws SQLException {
		template.update(INSERT, new Object[] { bauthor.getGenre_id(), bauthor.getBookId() });
	}

	public void deleteBooksByGenre(int genreId) throws SQLException {

		template.update(DELETE_BY_GENRE_ID, new Object[] { genreId });

	}

	public void deleteGenresByBook(int bookId) throws SQLException {
		template.update(DELETE_BY_BOOK_ID, new Object[] {bookId });
	}

	public List<Book_Genre> readAll() throws SQLException {
		return template.query("select * from tbl_book_genres", this);
	}

	@Override
	public List<Book_Genre> extractData(ResultSet rs) throws SQLException {
		List<Book_Genre> book_genres = new ArrayList<Book_Genre>();
		while (rs.next()) {
			Book_Genre book_genre = new Book_Genre();
			book_genre.setGenre_id(rs.getInt("genre_id"));
			book_genre.setBookId(rs.getInt("bookId"));
			book_genres.add(book_genre);
		}
		return book_genres;
	}

	public Book_Genre exist(Book_Genre ba) {
		List<Book_Genre> newba = template.query(SELECT_IF_EXIST, new Object[] { ba.getBookId(), ba.getGenre_id() },
				this);
		if (newba.isEmpty())
			return null;
		return newba.get(0);
	}

	public List<Book_Genre> getbyBook(Book_Genre item) {
		List<Book_Genre> newba = template.query(SELECT_BY_BOOK_ID, new Object[] { item.getBookId() }, this);
		if (newba.isEmpty())
			return null;
		return newba;
	}

	public List<Book_Genre> getbyGenre(Book_Genre item) {
		List<Book_Genre> newba = template.query(SELECT_BY_GENRE_ID, new Object[] { item.getGenre_id() }, this);
		if (newba.isEmpty())
			return null;
		return newba;
	}

	public void addGenresForBook(int bookId, int[] genreIds) {
		for(int id : genreIds){
			template.update(INSERT, new Object[] {id, bookId});
		}
		
	}

	public void updateGenresForBook(int[] genreIds, Book book) throws SQLException {
		deleteGenresByBook(book.getBookId());
		for(int id : genreIds){
			template.update(INSERT, new Object[] {id, book.getBookId()});
		}
	}
}
