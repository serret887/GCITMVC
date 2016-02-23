package com.gcit.training.spring.lms.dao.book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.gcit.training.spring.lms.dao.BaseDAO;
import com.gcit.training.spring.lms.dao.DAO;
import com.gcit.training.spring.lms.entity.Author;
import com.gcit.training.spring.lms.entity.Book;

public class BookDAO extends BaseDAO<Book> implements DAO<Book> {

	private static final String DELETE = "delete from tbl_book where bookId = ?";
	private static final String UPDATE = "update tbl_book set title = ?, pubId = ? where bookId = ?";
	private static final String BOOKS_AVAILABLES_FOR_BRANCH_ID = "select tbl_book.bookId,tbl_book.title,tbl_book.pubId"
			+ " from tbl_book join tbl_book_copies "
			+ "on tbl_book_copies.bookId = tbl_book.bookId where tbl_book_copies.branchId = ?"
			+ " and tbl_book_copies.noOfCopies > 0 and tbl_book_copies.noOfCopies is not null"
			+ " and tbl_book.bookId not in ( select tbl_book.bookId from tbl_book"
			+ " JOIN tbl_book_loans on tbl_book_loans.bookId = tbl_book.bookId "
			+ "and tbl_book_loans.branchId = ? and tbl_book_loans.cardNo = ? )";
	private static final String BOOKS_OWN_BY_CARD_No = "select tbl_book.bookId,tbl_book.title,tbl_book.pubId"
			+ " from  tbl_book join tbl_book_loans " + "on tbl_book_loans.bookId = tbl_book.bookId "
			+ "where  tbl_book_loans.cardNo = ?";
	private static final String SELECT = "select * from tbl_book";
	private static final String GET_BOOK_BY_ID = "select * from tbl_book where bookId = ?";
	private static final String GET_BOOKS_BY_BRANCH_ID = "select * from tbl_book "
			+ " join tbl_book_copies on tbl_book.bookId = tbl_book_copies.bookId "
			+ " where tbl_book_copies.branchId = ?";

	private final static String INSERT = "insert into tbl_book (title, pubId) values(?, ?)";

	@Autowired
	ExtractFirstDataBook extractFirstData;

	@Autowired
	ExtractFullDataBook extractFullData;

	public List<Book> getBooksByBranchId(int branchId) throws SQLException {
		return template.query(GET_BOOKS_BY_BRANCH_ID, new Object[] { branchId }, extractFullData);
	}

	public List<Book> getBooksByPublisher(int pubId) {
		return template.query("select * from tbl_book where pubId = ?", new Object[] { pubId }, extractFirstData);
	}

	public List<Book> getBooksAvailableInLibraryForCardNo(int branchId, int cardNo) throws SQLException {
		Integer[] branchIds = new Integer[] { branchId, branchId, cardNo };
		return template.query(BOOKS_AVAILABLES_FOR_BRANCH_ID, branchIds, extractFullData);
	}

	public List<Book> getBooksOwnForCardNo(int cardNo) throws SQLException {
		return template.query(BOOKS_OWN_BY_CARD_No, new Object[] { cardNo }, extractFirstData);
	}

	@Override
	public List<Book> readAll(int pageNo) {
		if (pageNo != -1) {
			setPageNo(pageNo);
			int[] minMax = limits();
			return template.query("select * from tbl_book limit ?,?", new Object[] { minMax[0], getPageSize() },
					extractFullData);
		}
		return template.query(SELECT, extractFullData);

	}

	@Override
	public List<Book> readFirstLevel(int pageNo) {
		return template.query(SELECT, extractFirstData);
	}

	@Override
	public int add(Book item) {
		final String title = item.getTitle();
		final int pub = item.getPublisher().getPublisherId();

		KeyHolder keyHolder = new GeneratedKeyHolder();
		template.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(INSERT, new String[] { "id" });
				ps.setString(1, title);
				ps.setInt(2, pub);
				return ps;
			}
		}, keyHolder);
		return keyHolder.getKey().intValue();
	}

	@Override
	public Book getById(int id) {
		List<Book> b = template.query(GET_BOOK_BY_ID, new Object[] { id }, extractFullData);
		if (b.isEmpty())
			return null;
		return b.get(0);
	}

	@Override
	public List<Book> searchByName(String searchString, int pageNo) {

		if (searchString == "") {
			return readAll(pageNo);
		}
		if (pageNo != -1) {
			setPageNo(pageNo);
			int[] minMax = limits();
			final String SELECT_BY_NAME = " select * from tbl_book where title like '%" + searchString
					+ "%' limit ? , ?";

			List<Book> a = template.query(SELECT_BY_NAME, new Object[] { minMax[0], getPageSize() }, extractFullData);
			return a;
		} else {
			return template.query("select * from tbl_book where title like '%" + searchString + "%'", extractFullData);
		}

	}

	@Override
	public void delete(Book item) {
		template.update(DELETE, new Object[] { item.getBookId() });
	}

	@Override
	public void update(Book item) {
		template.update(UPDATE,
				new Object[] { item.getTitle(), item.getPublisher().getPublisherId(), item.getBookId() });
	}
}
