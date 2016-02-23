package com.gcit.training.spring.lms.entity;

import java.io.Serializable;


public class LibraryBranch implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = 7512568635649931980L;
private int branchId;
	public int getBranchId() {
	return branchId;
}

public void setBranchId(int branchId) {
	this.branchId = branchId;
}

	private String branchName,branchAddress;

	public String getBranchName() {
		return branchName;
	}

	public String getBranchAddress() {
		return branchAddress;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public void setBranchAddress(String branchAddress) {
		this.branchAddress = branchAddress;
	}
	
	
}
