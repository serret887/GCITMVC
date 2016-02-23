package com.gcit.training.spring.lms.service.entities;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.gcit.training.lms.services.BorrowerServiceTest;
import com.gcit.training.spring.lms.dao.Book_CopiesDAO;
import com.gcit.training.spring.lms.dao.bookLoans.BookLoansDAO;
import com.gcit.training.spring.lms.entity.Book;
import com.gcit.training.spring.lms.entity.Books_Loans;
import com.gcit.training.spring.lms.entity.Borrower;
import com.gcit.training.spring.lms.entity.LibraryBranch;

public class BookLoansService {

	@Autowired
	BookLoansDAO bookLoansDAO;

	@Autowired
	Book_CopiesDAO bookCopiesDAO;

	public boolean checkOutBook(int branchId, int bookId, int cardNo) {
		System.out.println(branchId +" ->"+bookId+" ->"+ cardNo);
		Books_Loans item = createLoan(branchId, bookId, cardNo);
		bookLoansDAO.add(item);
		// extract one book from that branch
		try {
			bookCopiesDAO.extractOneBook(bookId, branchId);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean checkInBook(int branchId, int bookId, int cardNo) {
		Books_Loans item = new Books_Loans();
		Book book = new Book();
		book.setBookId(bookId);
		item.setBook(book);
		
		Borrower borrower = new Borrower();
		borrower.setCarNo(cardNo);
		item.setBorrower(borrower);
		
		LibraryBranch branch = new LibraryBranch();
		branch.setBranchId(branchId);
		item.setBranch(branch);
		
		item.setDateIn(new Timestamp(System.currentTimeMillis()));
		bookLoansDAO.update(item);
		// extract one book from that branch
		try {
			bookCopiesDAO.extractOneBook(bookId, branchId);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public List<Books_Loans> get_All_BookLoansForSpecificCard(int cardNo) throws SQLException {
		return bookLoansDAO.getLoansForCardNo(cardNo);
	}

	public List<Books_Loans> get_Due_BookLoansForSpecificCard(int cardNo) throws SQLException {
		return bookLoansDAO.getDueLoansForCardNo(cardNo);
	}

	private Books_Loans createLoan(int branchId, int bookId, int cardNo) {

		Books_Loans bookLoans = new Books_Loans();
		Book book = new Book();
		book.setBookId(bookId);

		LibraryBranch branch = new LibraryBranch();
		branch.setBranchId(branchId);

		Borrower borrower = new Borrower();
		borrower.setCarNo(cardNo);

		bookLoans.setBook(book);
		bookLoans.setBranch(branch);
		bookLoans.setBorrower(borrower);
		// day of today
		long time = System.currentTimeMillis();
		bookLoans.setDateOut(new Timestamp(time));
		// adding 7 days
		Calendar c = Calendar.getInstance();
		c.setTime(new Date(time)); // Now use today date.
		c.add(Calendar.DATE, 7);
		bookLoans.setDueDate(new Timestamp(c.getTimeInMillis()));
		// set date in to null
		bookLoans.setDateIn(null);
		return bookLoans;
	}

}
