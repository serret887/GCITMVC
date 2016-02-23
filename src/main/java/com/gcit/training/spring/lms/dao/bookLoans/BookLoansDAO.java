package com.gcit.training.spring.lms.dao.bookLoans;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gcit.training.spring.lms.dao.BaseDAO;
import com.gcit.training.spring.lms.dao.DAO;
import com.gcit.training.spring.lms.entity.Books_Loans;

public class BookLoansDAO extends BaseDAO<Books_Loans> implements DAO<Books_Loans> {

	private static final String LOANS_OF_CARD_No = "select * from tbl_book_loans where cardNo = ?";

	private static final String DUE_LOANS_FOR_CARD_No = "select * from tbl_book_loans where cardNo = ? "
			+ " and (dateIn = '' or dateIn is null)";

	private static final String SELECT = "select * from tbl_book_loans";

	private static final String UPDATE_DATE_IN = "update tbl_book_loans set dateIn = ? "
			+ "where bookId = ? and branchId = ? and cardNo = ? ";

	private static final String DELETE = "delete from tbl_book_loans where bookId = ? and branchId = ? and cardNo = ?  ";

	private static final String INSERT = "insert into tbl_book_loans (bookId,branchId,cardNo,dateOut,dueDate,dateIn)"
			+ " values(?, ?, ?, ?, ?, ?)";

	private static final String DELETE_BY_BORROWER = "delete from tbl_book_loans where cardNo = ?  ";

	private static final String DELETE_BY_BRANCH = "delete from tbl_book_loans where branchId = ?   ";

	private static final String DELETE_BY_BOOK = "delete from tbl_book_loans where bookId = ? ";

	@Autowired
	ExtractFirstDataBookLoans extractFirstData;
	
	@Autowired
	ExtractFullDataBookLoans extractFullData;

	/*
	 * al teh loans of an specific card
	 */
	public List<Books_Loans> getLoansForCardNo(int cardNo) throws SQLException {
		return template.query(LOANS_OF_CARD_No, new Object[] { cardNo }, extractFullData);

	}

	/*
	 * all teh loans taht an specific card owns
	 */
	public List<Books_Loans> getDueLoansForCardNo(int cardNo) throws SQLException {
		return template.query(DUE_LOANS_FOR_CARD_No, new Object[] { cardNo }, extractFirstData);

	}

	@Override
	public List<Books_Loans> readAll(int pageNo) {
		return template.query(SELECT, extractFullData);
	}

	@Override
	public List<Books_Loans> readFirstLevel(int pageNo) {
		return template.query(SELECT, extractFirstData);
	}

	@Override
	public int add(Books_Loans item) {
		return template.update(INSERT, new Object[] { item.getBook().getBookId(), item.getBranch().getBranchId(),
				item.getBorrower().getCarNo(), item.getDateOut(), item.getDueDate(), item.getDateIn() });
	}

	@Override
	public Books_Loans getById(int id) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<Books_Loans> searchByName(String searchString, int pageNo) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void delete(Books_Loans item) {
		template.update(DELETE, new Object[] { item.getBook().getBookId(), item.getBranch().getBranchId(),
				item.getBorrower().getCarNo() });

	}

	@Override
	public void update(Books_Loans item) {
		System.out.println(item.getDateIn().toString());
		System.out.println(item.getBook().getBookId());
		System.out.println(item.getBranch().getBranchId());
		System.out.println(item.getBorrower().getCarNo());
		System.out.println();
		System.out.println();
		template.update(UPDATE_DATE_IN, new Object[] { item.getDateIn(), item.getBook().getBookId(),
				item.getBranch().getBranchId(), item.getBorrower().getCarNo() });

	}

	public void deleteByBorrower(int cardNo) {
		template.update(DELETE_BY_BORROWER, new Object[] { cardNo });
	}

	public void deleteByBranch(int branchId) {
		template.update(DELETE_BY_BRANCH, new Object[] { branchId });
	}

	public void deleteByBook(int bookId) {
		template.update(DELETE_BY_BOOK, new Object[] { bookId });

	}

}
