package com.gcit.training.spring.lms.dao.publisher;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.gcit.training.spring.lms.dao.book.ExtractFirstDataBook;
import com.gcit.training.spring.lms.entity.Publisher;
//TODO need to be autowired with the bookDAO

public class ExtractFullDataPublisher implements ResultSetExtractor<List<Publisher>> {
	@Autowired
	JdbcTemplate template;
	@Autowired
	ExtractFirstDataBook fbdao;

	@Override
	public List<Publisher> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<Publisher> publishers = new ArrayList<Publisher>();
		while (rs.next()) {
			Publisher publisher = new Publisher();
			publisher.setPublisherName(rs.getString("publisherName"));
			publisher.setPublisherId(rs.getInt("publisherId"));
			publisher.setPublisherAddress(rs.getString("publisherAddress"));
			publisher.setBooks(template.query("select * from tbl_book where pubId= ?",
					new Object[] { publisher.getPublisherId() }, fbdao));
			publisher.setPublisherPhone(rs.getString("publisherPhone"));
			publishers.add(publisher);
		}
		return publishers;
	}
}
