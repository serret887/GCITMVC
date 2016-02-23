package com.gcit.training.spring.lms.service.entities;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.gcit.training.spring.lms.dao.Book_CopiesDAO;
import com.gcit.training.spring.lms.dao.LibraryDAO;
import com.gcit.training.spring.lms.dao.book.BookDAO;
import com.gcit.training.spring.lms.entity.Book;
import com.gcit.training.spring.lms.entity.LibraryBranch;

public class LibraryService {
	@Autowired
	Book_CopiesDAO bcdao;

	@Autowired
	LibraryDAO branchDAO;
	
	@Autowired
	BookDAO bookDao;

	// ////////////////////////////////// AUTHORS///////////////////////////
	public int addLibraryBranch(LibraryBranch branch) {
		return branchDAO.add(branch);
	}

	public List<LibraryBranch> readLibraries(int pageNo) {
		return branchDAO.readAll(pageNo);
	}

	public void editLibraryBranch(LibraryBranch branch) {
		branchDAO.update(branch);
	}

	public void deleteLibraryBranch(LibraryBranch branch) throws SQLException {
		bcdao.deleteByBranch(branch.getBranchId());
		branchDAO.delete(branch);
	}

	public LibraryBranch getLibraryBranchById(int id) {
		return branchDAO.getById(id);
	}
	////////////////////////////////////////////////// LibraryBranch////////////////////////////


	
	
	public void addBooksCopiesInBranch(int branchId, int bookId, Integer amount) throws SQLException {
		amount = (amount != null) ? amount : 0;
		amount = (amount < 0) ? 0 : amount;
		bcdao.updateBooksAmount(branchId, bookId, amount);
	}

	public void addBookInBranches(int[] branchIds, int bookId, Integer amount) {
		amount = (amount != null) ? amount : 0;
		amount = (amount < 0) ? 0 : amount;
		bcdao.addBookInBranches(branchIds, bookId, amount);
	}
	
	public List<Book> getAllBooksInBranch(int branchId) throws SQLException{
		return bookDao.getBooksByBranchId(branchId);
	}
	
	public int getAmountOfBooksByBranch(int branchId, int bookId) throws SQLException{
		return bcdao.getAmountOfCopiesOfBookInBranch(bookId, branchId);
	}

}
