package com.gcit.training.spring.lms.dao.book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.gcit.training.spring.lms.dao.author.ExtractFirstDataAuthor;
import com.gcit.training.spring.lms.dao.genre.ExtractFirstDataGenre;
import com.gcit.training.spring.lms.dao.publisher.ExtractFirstDataPublisher;
import com.gcit.training.spring.lms.entity.Book;

public class ExtractFullDataBook implements ResultSetExtractor<List<Book>> {

	@Autowired
	ExtractFirstDataAuthor fadao;
	@Autowired
	ExtractFirstDataGenre fgdao;
	@Autowired
	ExtractFirstDataPublisher fpdao;
	@Autowired
	JdbcTemplate template;

	@Override
	public List<Book> extractData(ResultSet rs) {
		List<Book> books = new ArrayList<Book>();
		// AuthorDAO adao = new AuthorDAO(conn);
		// GenreDAO gdao = new GenreDAO(conn);
		// PublisherDAO pdao = new PublisherDAO(conn);

		try {
			while (rs.next()) {
				Book book = new Book();
				book.setTitle(rs.getString("title"));
				book.setBookId(rs.getInt("bookId"));

				book.setAuthors(template.query(
						"select * from tbl_author where authorId IN "
								+ "(select authorId from tbl_book_authors where bookId = ?)",
						new Object[] { book.getBookId() }, fadao));
				book.setGenre(template.query(
						"select * from tbl_genre where genre_id IN "
								+ "(select genre_id from tbl_book_genres where bookId = ?)",
						new Object[] { book.getBookId() }, fgdao));
				book.setPublisher(template.query("select * from " + "tbl_publisher where publisherId = ?",
						new Object[] { rs.getInt("pubId") }, fpdao).get(0));
				books.add(book);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return books;
	}
}
