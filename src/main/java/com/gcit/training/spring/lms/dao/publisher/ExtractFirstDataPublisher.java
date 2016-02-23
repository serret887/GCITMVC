package com.gcit.training.spring.lms.dao.publisher;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.gcit.training.spring.lms.entity.Publisher;

public class ExtractFirstDataPublisher implements ResultSetExtractor<List<Publisher>>{
	
	private static final String PUBLISHER_PHONE = "publisherPhone";
	private static final String PUBLISHER_ID = "PublisherId";
	private static final String PUBLISHER_ADDRESS = "publisherAddress";
	private static final String PUBLISHER_NAME = "publisherName";
	
	@Override
	public List<Publisher> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<Publisher> publishers = new ArrayList<Publisher>();
		while (rs.next()) {
			Publisher publisher = new Publisher();
			publisher.setPublisherName(rs.getString(PUBLISHER_NAME));
			publisher.setPublisherAddress(rs.getString(PUBLISHER_ADDRESS));
			publisher.setPublisherId(rs.getInt(PUBLISHER_ID));
			publisher.setPublisherPhone(rs.getString(PUBLISHER_PHONE));
			publishers.add(publisher);
		}
		return publishers;
	}

}
