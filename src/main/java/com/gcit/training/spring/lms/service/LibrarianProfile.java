package com.gcit.training.spring.lms.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.gcit.training.spring.lms.entity.Book;
import com.gcit.training.spring.lms.entity.LibraryBranch;
import com.gcit.training.spring.lms.service.entities.LibraryService;

public class LibrarianProfile {

	
	@Autowired
	LibraryService lib;

	public List<LibraryBranch> getLibraries(int pageNo) throws SQLException {
		return lib.readLibraries(pageNo);
	}
	
	public void addBooksToLibrary(int branchId, int amount, int bookId) throws SQLException{
		lib.addBooksCopiesInBranch(branchId, bookId, amount);
	}
	
	public List<Book> getAllBooksInLibrary(int branchId) throws SQLException{
		return lib.getAllBooksInBranch(branchId);
	}
	public int getAmountOfBookByBranch(int bookId,int branchId) throws SQLException{
		return lib.getAmountOfBooksByBranch(branchId, bookId);
	}
	

	@Transactional
	public void editLibrary(LibraryBranch library) {
		lib.editLibraryBranch(library);
	}
	
	public String getLibraryName(int branchId) throws SQLException{
		return lib.getLibraryBranchById(branchId).getBranchName();
	}
}
