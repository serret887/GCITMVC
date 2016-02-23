package com.gcit.training.spring.lms.dao.book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.gcit.training.spring.lms.entity.Book;

public class ExtractFirstDataBook implements ResultSetExtractor<List<Book>> {

	@Override
	public List<Book> extractData(ResultSet rs) throws SQLException {
		List<Book> books = new ArrayList<Book>();
			while (rs.next()) {
				Book book = new Book();
				book.setTitle(rs.getString("title"));
				book.setBookId(rs.getInt("bookId"));
				books.add(book);
			}
		return books;
	}

}
