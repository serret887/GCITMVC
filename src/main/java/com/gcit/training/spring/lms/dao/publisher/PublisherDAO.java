package com.gcit.training.spring.lms.dao.publisher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.gcit.training.spring.lms.dao.BaseDAO;
import com.gcit.training.spring.lms.dao.DAO;
import com.gcit.training.spring.lms.entity.Publisher;

public class PublisherDAO extends BaseDAO<Publisher> implements DAO<Publisher> {

	private static final String UPDATE_PUBLISHER = "update tbl_publisher set publisherName = ?, publisherAddress = ?, publisherPhone = ? where publisherId = ?";
	private static final String SEARCH_BY_NAME = "select * from tbl_publisher where publisherName like ? ";
	private static final String DELETE_BY_PUBLISHER_ID = "delete from tbl_publisher where publisherId = ?";
	private static final String SELECT_BY_ID = "select * from tbl_publisher where publisherId = ?";
	private static final String INSERT_PUBLISHER = "insert into tbl_Publisher (publisherName , publisherAddress , publisherPhone) values( ? , ? , ? )";
	//// public void deletePublisher(Publisher publisher) throws SQLException {
	//// // first I nedd to retrieve the list of books of this publisher
	//// List<Publisher> publishers = read("select * from tbl_publisher where
	//// publisherId = ?",
	//// new Object[] { publisher.getPublisherId() });
	////
	//// // wil delete all the books of his editorial
	//// BookDAO bdao = new BookDAO(conn);
	//// for (Publisher p : publishers) {
	//// if (!p.getBooks().isEmpty())
	//// for (Book b : p.getBooks())
	//// bdao.deleteBook(b);
	//// }
	//
	// save("delete from tbl_publisher where PublisherId = ?", new Object[] {
	//// publisher.getPublisherId() });
	// }
	//
	// public void updatePublisher(Publisher publisher) throws SQLException {
	// save("update tbl_publisher set publisherName = ?, publisherAddress = ?,
	//// publisherPhone = ? where publisherId = ?",
	// new Object[] { publisher.getPublisherName(),
	//// publisher.getPublisherAddress(),
	// publisher.getPublisherPhone(), publisher.getPublisherId() });
	// }
	//
	// public List<Publisher> readAll() throws SQLException {
	// return read("select * from tbl_publisher", null);
	// }
	//
	// @Override
	// public List<Publisher> extractData(ResultSet rs) {
	// List<Publisher> Publishers = new ArrayList<Publisher>();
	// BookDAO adao = new BookDAO(conn);
	// try {
	// while (rs.next()) {
	// Publisher Publisher = new Publisher();
	// Publisher.setPublisherName(rs.getString("publisherName"));
	// Publisher.setPublisherId(rs.getInt("publisherId"));
	// Publisher.setPublisherAddress(rs.getString("publisherAddress"));
	// Publisher.setBooks(adao.readFirstLevel("select * from tbl_book where
	//// pubId= ?",
	// new Object[] { Publisher.getPublisherId() }));
	// Publisher.setPublisherPhone(rs.getString("publisherPhone"));
	// Publishers.add(Publisher);
	// }
	// } catch (SQLException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// return Publishers;
	// }
	//

	private static final String PUBLISHER_PHONE = "publisherPhone";
	private static final String PUBLISHER_ID = "PublisherId";
	private static final String PUBLISHER_ADDRESS = "publisherAddress";
	private static final String PUBLISHER_NAME = "publisherName";

	private final String SELECT_ALL = "select * from tbl_publisher";

	@Autowired
	private ExtractFirstDataPublisher extractFirstData;

	@Autowired
	private ExtractFullDataPublisher extractFullData;
	
	@Override
	public List<Publisher> readAll(int pageNo) {
		return template.query(SELECT_ALL, extractFullData);
	}

	@Override
	public List<Publisher> readFirstLevel(int pageNo) {
		return template.query(SELECT_ALL, extractFirstData);
	}

	@Override
	public int add(Publisher item) {
		final String pubName = item.getPublisherName();
		final String pubPhone = item.getPublisherPhone();
		final String pubAddress = item.getPublisherAddress();

		KeyHolder keyHolder = new GeneratedKeyHolder();
		template.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(INSERT_PUBLISHER, new String[] { "id" });
				ps.setString(1, pubName);
				ps.setString(2, pubAddress);
				ps.setString(3, pubPhone);
				return ps;
			}
		}, keyHolder);
		return keyHolder.getKey().intValue();
	}

	@Override
	public Publisher getById(int id) {
		List<Publisher> publisher = template.query(SELECT_BY_ID, new Object[] { id }, extractFullData);
		if(publisher.isEmpty())
			return null;
		return publisher.get(0);
	}

	@Override
	public List<Publisher> searchByName(String searchString, int pageNo) {
		return template.query(SEARCH_BY_NAME, new Object[] { searchString }, extractFirstData);
	}

	@Override
	public void delete(Publisher item) {
		template.update(DELETE_BY_PUBLISHER_ID, new Object[] { item.getPublisherId() });
	}

	@Override
	public void update(Publisher item) {
		template.update(UPDATE_PUBLISHER, new Object[] { item.getPublisherName(), item.getPublisherAddress(),
				item.getPublisherPhone(), item.getPublisherId() });

	}

}