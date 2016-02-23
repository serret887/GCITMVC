package com.gcit.training.spring.lms.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class Books_Loans  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2762205437616171684L;
	private Book book;
	private LibraryBranch branch;
	private Borrower borrower;
	private Timestamp dateOut, dueDate, dateIn;

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public LibraryBranch getBranch() {
		return branch;
	}

	public void setBranch(LibraryBranch branch) {
		this.branch = branch;
	}

	public Borrower getBorrower() {
		return borrower;
	}

	public void setBorrower(Borrower borrower) {
		this.borrower = borrower;
	}

	public Timestamp getDateOut() {
		return dateOut;
	}

	public void setDateOut(Timestamp dateOut) {
		this.dateOut = dateOut;
	}

	public Timestamp getDueDate() {
		return dueDate;
	}

	public void setDueDate(Timestamp dueDate) {
		this.dueDate = dueDate;
	}

	public Timestamp getDateIn() {
		return dateIn;
	}

	public void setDateIn(Timestamp dateIn) {
		this.dateIn = dateIn;
	}

}
