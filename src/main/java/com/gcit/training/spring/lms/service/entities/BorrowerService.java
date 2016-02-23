package com.gcit.training.spring.lms.service.entities;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.gcit.training.spring.lms.dao.BorrowerDAO;
import com.gcit.training.spring.lms.dao.bookLoans.BookLoansDAO;
import com.gcit.training.spring.lms.entity.Books_Loans;
import com.gcit.training.spring.lms.entity.Borrower;

public class BorrowerService {

	@Autowired
	BorrowerDAO borrowerDAO;

	@Autowired
	BookLoansDAO bookLoansDAO;

	@Autowired
	BookLoansService bookLoansService;

	// ////////////////////////////////// BORROWER///////////////////////////
	public int addBorrower(Borrower borrower) {
		return borrowerDAO.add(borrower);
	}

	public List<Borrower> readBorrower(int pageNo) {
		return borrowerDAO.readAll(pageNo);
	}
	

	public void editBorrower(Borrower borrower) {
		borrowerDAO.update(borrower);
	}

	public void deleteBorrower(Borrower borrower) {
		borrowerDAO.delete(borrower);
		bookLoansDAO.deleteByBorrower(borrower.getCarNo());
	}

	public Borrower getBorrowerById(int id) {
		return borrowerDAO.getById(id);
	}
	////////////////////////////////////////////////// BOOKS////////////////////////////

	public List<Borrower> readFirstInfo(int i) {
		return borrowerDAO.readFirstLevel(i);
	}

	public boolean checkOut(int bookId, int branchId, int cardNo) {
		return bookLoansService.checkOutBook(branchId, bookId, cardNo);
	}

	public boolean checkIn(int bookId, int branchId, int cardNo) {

		return bookLoansService.checkInBook(branchId, bookId, cardNo);
	}
	


}