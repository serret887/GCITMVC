package com.gcit.training.spring.lms.dao.genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.gcit.training.spring.lms.dao.book.ExtractFirstDataBook;
import com.gcit.training.spring.lms.entity.Genre;
import com.gcit.training.spring.lms.entity.Publisher;
//TODO need to be autowired with the bookDAO

public class ExtractFullDataGenre implements ResultSetExtractor<List<Genre>> {
	@Autowired
	JdbcTemplate template;

	@Autowired
	ExtractFirstDataBook fbdao;

	private static final String GENRE_NAME = "genre_name";
	private static final String GENRE_ID = "genre_id";

	@Override
	public List<Genre> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<Genre> genres = new ArrayList<Genre>();
		while (rs.next()) {
			Genre genre = new Genre();
			genre.setGenreName(rs.getString(GENRE_NAME));
			genre.setGenreId(rs.getInt(GENRE_ID));
			genre.setBooks(template.query(
					"select * from tbl_book where bookId IN "
							+ "(select bookId from tbl_book_genres where genre_id = ?)",
					new Object[] { genre.getGenreId() }, fbdao));
			genres.add(genre);
		}
		return genres;
	}
}
