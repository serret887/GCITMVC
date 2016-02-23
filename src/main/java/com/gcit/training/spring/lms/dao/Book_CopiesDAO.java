package com.gcit.training.spring.lms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.gcit.training.spring.lms.entity.Books_Copies;

public class Book_CopiesDAO extends BaseDAO<Books_Copies> implements ResultSetExtractor<List<Books_Copies>> {

	private static final String UPDATE_BY_BOOK_ID_AND_BRANCH_ID_REMOVE = "update tbl_book_copies set noOfCopies = noOfCopies -1 where bookId = ? and branchId = ? ";
	private static final String SELECT_BY_BOOK_ID_AND_BRANCH_ID = "select * from tbl_book_copies where bookId = ? and branchId = ?";
	private static final String SELECT = "select * from tbl_book_copies";
	private static final String ADD_No_OF_COPIES = "update tbl_book_copies set noOfCopies = noOfCopies + ? where  bookId = ? and branchId = ?";
	private static final String DELETE_BY_BRANCH_ID_AND_BOOK_ID = "delete from tbl_book_copies where branchId = ? and bookId = ?";
	private static final String INSERT = "insert into tbl_book_copies (bookId,branchId,noOfCopies) values( ? , ?, ?)";
	private static final String UPDATE_No_OF_COPIES_ADD = "update tbl_book_copies set noOfCopies = noOfCopies +1 where  bookId = ? and branchId = ? ";

	public int getAmountOfCopiesOfBookInBranch(int bookId, int branchId) throws SQLException {
		List<Books_Copies> amount = template.query(SELECT_BY_BOOK_ID_AND_BRANCH_ID, new Object[] { bookId, branchId },
				this);
		if (amount.isEmpty())
			return 0;
		return amount.get(0).getNoOfCopies();
	}

	public void extractOneBook(int bookId, int branchId) throws SQLException {
		List<Books_Copies> boo_copies = template.query(SELECT_BY_BOOK_ID_AND_BRANCH_ID,
				new Object[] { bookId, branchId }, this);
		if (!boo_copies.isEmpty() && boo_copies.get(0).getNoOfCopies() > 0)
			template.update(UPDATE_BY_BOOK_ID_AND_BRANCH_ID_REMOVE, new Object[] { bookId, branchId });

	}

	public void addOneBook(int bookId, int branchId) throws SQLException {
		template.update(UPDATE_No_OF_COPIES_ADD, new Object[] { bookId, branchId });

	}

	public void addBooks_Copies(Books_Copies bookCopies) throws SQLException {
		deleteBooks_Copies(bookCopies);
		template.update(INSERT,
				new Object[] { bookCopies.getBookId(), bookCopies.getBranchId(), bookCopies.getNoOfCopies() });
	}

	public void deleteBooks_Copies(Books_Copies bookCopies) throws SQLException {
		template.update(DELETE_BY_BRANCH_ID_AND_BOOK_ID,
				new Object[] { bookCopies.getBranchId(), bookCopies.getBookId() });
	}

	public void updateBooksAmount(int branchId, int bookId, int amount) throws SQLException {
		template.update(ADD_No_OF_COPIES, new Object[] { amount, bookId, branchId });
	}

	public List<Books_Copies> readAll() throws SQLException {
		return template.query(SELECT, this);
	}

	@Override
	public List<Books_Copies> extractData(ResultSet rs) throws SQLException {
		List<Books_Copies> book_copies = new ArrayList<Books_Copies>();

		while (rs.next()) {
			Books_Copies book_copy = new Books_Copies();
			book_copy.setBranchId(rs.getInt("branchId"));
			book_copy.setBookId(rs.getInt("bookId"));
			book_copy.setNoOfCopies(rs.getInt("noOfCopies"));
			book_copies.add(book_copy);
		}
		return book_copies;
	}

	public void addBooksForBranchId(int branchId, int[] bookIds, int amount) {
		for (int id : bookIds) {
			template.update(INSERT, new Object[] { id, branchId, amount });
		}
	}

	public void addBookInBranches(int[] branchIds, int bookId, Integer amount) {
		for (int id : branchIds) {
			template.update(INSERT, new Object[] { bookId, id, amount });
		}
	}

	public void deleteByBook(int bookId) {
		template.update("delete from tbl_book_copies where bookId = ?", new Object[] { bookId });
	}

	public void deleteByBranch(int branchId) {
		template.update("delete from tbl_book_copies where branchId = ?", new Object[] { branchId });
	}

}
