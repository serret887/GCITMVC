package com.gcit.training.spring.lms.entity;

import java.io.Serializable;


public class Books_Copies  implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = 81103091211891368L;
private int bookId,branchId,noOfCopies;

public int getBookId() {
	return bookId;
}

public int getBranchId() {
	return branchId;
}

public int getNoOfCopies() {
	return noOfCopies;
}

public void setBookId(int bookId) {
	this.bookId = bookId;
}

public void setBranchId(int branchId) {
	this.branchId = branchId;
}

public void setNoOfCopies(int noOfCopies) {
	this.noOfCopies = noOfCopies;
}

}
