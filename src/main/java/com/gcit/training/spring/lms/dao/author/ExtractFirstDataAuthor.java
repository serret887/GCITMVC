package com.gcit.training.spring.lms.dao.author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.gcit.training.spring.lms.entity.Author;
import com.gcit.training.spring.lms.entity.Genre;
import com.gcit.training.spring.lms.entity.Publisher;

public class ExtractFirstDataAuthor implements ResultSetExtractor<List<Author>> {

	private static final String AUTHOR_NAME = "authorName";
	private static final String AUTHOR_ID = "authorId";

	@Override
	public List<Author> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<Author> authors = new ArrayList<Author>();

		while (rs.next()) {
			Author author = new Author();
			author.setAuthorId(rs.getInt(AUTHOR_ID));
			author.setAuthorName(rs.getString(AUTHOR_NAME));
			// author.setBooks(bookDAO
			// .read("select * from tbl_book where bookId IN (select bookId
			// from tbl_book_authors where authorId = ?)",
			// new Object[] { author.getAuthorId() }, this));
			authors.add(author);
		}
		return authors;
	}

}
