package com.gcit.training.lms.dao;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.gcit.training.spring.LMSConfig;
import com.gcit.training.spring.lms.dao.Book_CopiesDAO;
import com.gcit.training.spring.lms.entity.Books_Copies;

@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { LMSConfig.class })
@ActiveProfiles("dev")
public class BookCopiesDAOTest {

	@Autowired
	Book_CopiesDAO bcdao;

	@Test
	public void testgetAmountOfCopiesOfBookInBranch() throws SQLException {
		int bookId = 1;
		int branchId = 1;
		int bookAmount = bcdao.getAmountOfCopiesOfBookInBranch(bookId, branchId);
		assertEquals("teh amount is not equal", 19, bookAmount);
	}

	@Test
	@Transactional
	public void testExtractOneBook() throws SQLException {
		int bookId = 1;
		int branchId = 1;
		bcdao.extractOneBook(bookId, branchId);
		int bookAmount = bcdao.getAmountOfCopiesOfBookInBranch(bookId, branchId);
		assertEquals("teh amount is not equal", 18, bookAmount);
	}

	@Test
	@Transactional
	public void testAddOneBook() throws SQLException {
		int bookId = 1;
		int branchId = 1;
		bcdao.addOneBook(bookId, branchId);
		int bookAmount = bcdao.getAmountOfCopiesOfBookInBranch(bookId, branchId);
		assertEquals("teh amount is not equal", 20, bookAmount);
	}

	@Test
	@Transactional
	public void testAddBooks_Copies() throws SQLException {
		Books_Copies bc = new Books_Copies();
		bc.setBookId(1);
		bc.setBranchId(1);
		bc.setNoOfCopies(10);
		bcdao.addBooks_Copies(bc);
		int bookAmount = bcdao.getAmountOfCopiesOfBookInBranch(1, 1);
		assertEquals("teh amount is not equal", 10, bookAmount);
	}

	@Test
	@Transactional
	public void testDeleteBooks_Copies() throws SQLException {
		Books_Copies bc = new Books_Copies();
		bc.setBookId(1);
		bc.setBranchId(1);
		bc.setNoOfCopies(10);
		bcdao.deleteBooks_Copies(bc);
		int bookAmount = bcdao.getAmountOfCopiesOfBookInBranch(1, 1);
		assertEquals("teh amount is not equal", 0, bookAmount);

	}

	@Test
	@Transactional
	public void testUpdateBooksAmount() throws SQLException {
		Books_Copies bc = new Books_Copies();
		bc.setBookId(1);
		bc.setBranchId(1);
		bc.setNoOfCopies(10);
		bcdao.updateBooksAmount(bc);
		int bookAmount = bcdao.getAmountOfCopiesOfBookInBranch(1, 1);
		assertEquals("teh amount is not equal", 29, bookAmount);

	}

	@Test
	public void testReadAll() throws SQLException {
		List<Books_Copies> bc = bcdao.readAll();
		assertEquals("the size is not equal", 9, bc.size());
	}

	@Test
	@Transactional
	public void testAddBookInBranches() throws SQLException {
		bcdao.addBookInBranches(new int[] { 1, 2, 3 }, 18, 2);
		List<Books_Copies> bc = bcdao.readAll();
		assertEquals("the size is not equal", 12, bc.size());
	}

	@Test
	@Transactional
	public void testAddbooksForBranchIds() throws SQLException {
		bcdao.addBooksForBranchId(19, new int[] { 1, 2, 3 }, 2);
		List<Books_Copies> bc = bcdao.readAll();
		assertEquals("the size is not equal", 12, bc.size());
	}

}
