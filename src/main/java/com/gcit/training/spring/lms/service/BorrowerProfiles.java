package com.gcit.training.spring.lms.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.gcit.training.spring.lms.dao.LibraryDAO;
import com.gcit.training.spring.lms.entity.Book;
import com.gcit.training.spring.lms.entity.Books_Loans;
import com.gcit.training.spring.lms.entity.Borrower;
import com.gcit.training.spring.lms.entity.LibraryBranch;
import com.gcit.training.spring.lms.service.entities.BookLoansService;
import com.gcit.training.spring.lms.service.entities.BookService;
import com.gcit.training.spring.lms.service.entities.BorrowerService;

public class BorrowerProfiles {

	@Autowired
	BookService bookS;
	@Autowired
	BookLoansService bls;

	@Autowired
	BorrowerService bs;

	@Autowired
	LibraryDAO libDao;
	
	public Borrower  getBorrowerById(int cardNo){
		return bs.getBorrowerById(cardNo);
	}

	public List<Book> getAllBooksAvailablesInBranch(int cardNo, int branchId) throws SQLException {
		return bookS.getBookAvailablesForBranch(branchId, cardNo);
	}
	
	public List<Book> getAllDueBooks(int cardNo) throws SQLException {
		return bookS.getBooksOwnByCardNo(cardNo);
	}
	
	public List<Books_Loans> getAllBookLoans(int cardNo) throws SQLException{
		return  bls.get_All_BookLoansForSpecificCard(cardNo);
	}

	public List<LibraryBranch> getAllBranches(int pageNo) {
		return libDao.readFirstLevel(pageNo);
	}
	@Transactional
	public boolean checkOut(int bookId, int branchId, int cardNo) {
		return bs.checkOut(branchId, bookId, cardNo);
	}
	@Transactional
	public boolean checIn(int bookId, int branchId, int cardNo) {
		return bs.checkIn(bookId, branchId, cardNo);
	}

	public boolean checkValidCard(int cardNo) {
		return (bs.getBorrowerById(cardNo) != null) ? true : false;
	}

	public List<Books_Loans> getAllTheLoans(int cardNo) throws SQLException {
		return bls.get_All_BookLoansForSpecificCard(cardNo);
	}

	public List<Books_Loans> getAllTheDueLoans(int cardNo) throws SQLException {
		return bls.get_Due_BookLoansForSpecificCard(cardNo);
	}
}
