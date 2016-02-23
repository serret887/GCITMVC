package com.gcit.training.lms.dao;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.sql.Timestamp;
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
import com.gcit.training.spring.lms.dao.bookLoans.BookLoansDAO;
import com.gcit.training.spring.lms.entity.Book;
import com.gcit.training.spring.lms.entity.Books_Loans;
import com.gcit.training.spring.lms.entity.Borrower;
import com.gcit.training.spring.lms.entity.LibraryBranch;

@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { LMSConfig.class })
@ActiveProfiles("dev")
public class BookLoansDAOTest {

	@Autowired
	BookLoansDAO bldao;

	private Books_Loans prepare() {
		Books_Loans item = new Books_Loans();
		item.setDateIn(new Timestamp(System.currentTimeMillis()));
		Book book = new Book();
		book.setBookId(3);
		item.setBook(book);
		LibraryBranch branch = new LibraryBranch();
		branch.setBranchId(1);
		item.setBranch(branch);
		Borrower borrower = new Borrower();
		borrower.setCarNo(1);
		item.setBorrower(borrower);
		return item;
	}

	@Test
	public void testGetLoansForCardNo() throws SQLException {
		List<Books_Loans> loans = bldao.getLoansForCardNo(1);
		assertEquals("the amount is incorrect", 3, loans.size());
	}

	@Test
	@Transactional
	public void testGetDueLoansForCardNo() throws SQLException {
		Books_Loans bl = prepare();
		bldao.update(bl);
		List<Books_Loans> loans = bldao.getDueLoansForCardNo(1);
		assertEquals("the amount is incorrect", 2, loans.size());
	}

	@Test
	public void testReadAll() {
		List<Books_Loans> bl = bldao.readAll(1);
		assertEquals("the amount is incorrect", 6, bl.size());
		assertNotNull("the no is books", bl.get(2).getBook());
		assertNotNull("the no is borrower", bl.get(2).getBorrower());
		assertNotNull("the no is borrower", bl.get(2).getBorrower());
	}

	@Test
	public void testReadFirstLevel() {
		List<Books_Loans> bl = bldao.readFirstLevel(1);
		assertEquals("the amount is incorrect", 6, bl.size());
		assertNull("the is books", bl.get(2).getBook());
	}

	@Test
	@Transactional
	public void testAdd() {
		bldao.delete(prepare());
		Books_Loans b = prepare();
		b.setDateOut(new Timestamp(System.currentTimeMillis()));
		b.setDueDate(new Timestamp(System.currentTimeMillis()));
		bldao.add(b);

	}

	@Test(expected = UnsupportedOperationException.class)
	public void testGetById() {
		bldao.getById(1);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testSearchByName() {
		bldao.searchByName("e", 1);
	}

	@Test
	@Transactional
	public void testDelete() {
		bldao.delete(prepare());
		assertEquals("the amount is not the same ", 5, bldao.readAll(1).size());
	}

	@Test
	@Transactional
	public void testUpdate() {
		Books_Loans bl = prepare();
		bldao.update(bl);
	}
	
	@Test
	@Transactional
	public void testDeleteByCardId(){
		int cardNo =1;
		bldao.deleteByBorrower(cardNo);
		assertEquals("the amount is not the same ", 3, bldao.readAll(1).size());
	}
	@Test
	@Transactional
	public void testDeleteByBranchId(){
		int branchId =1;
		bldao.deleteByBranch(branchId);
		assertEquals("the amount is not the same ", 4, bldao.readAll(1).size());
	}
	@Test
	@Transactional
	public void testDeleteByBookID(){
		int bookId =1;
		bldao.deleteByBook(bookId);
		assertEquals("the amount is not the same ", 5, bldao.readAll(1).size());
	}

}
