package com.gcit.training.lms.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.gcit.training.spring.LMSConfig;
import com.gcit.training.spring.lms.dao.Book_AuthorsDAO;
import com.gcit.training.spring.lms.entity.Author;
import com.gcit.training.spring.lms.entity.Book;
import com.gcit.training.spring.lms.entity.Book_Authors;

@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { LMSConfig.class })
@ActiveProfiles("dev")
public class Book_AuthorsDAOTest {

	@Autowired
	Book_AuthorsDAO abdao;

	@Test
	@Transactional
	public void insertAuthorsForBook() {
		int bookId = 24;
		int[] authorIds = { 1, 2, 3 };
		abdao.addAuthorsForBook(bookId, authorIds);
		List<Book_Authors> ba = abdao.readFirstLevel(1);
		assertEquals("the amount does not match", 9, ba.size());
	}

	@Test
	public void readFirstLevelTest() {
		List<Book_Authors> ba = abdao.readFirstLevel(1);
		assertEquals("the amount does not match", 6, ba.size());

	}

	/*
	 * testting tha t add is returning the right id
	 */
	@Test
	@Transactional
	public void testAdd() {
		Book_Authors item = new Book_Authors();
		item.setAuthorId(2);
		item.setBookId(3);
		abdao.add(item);
		assertNotNull(abdao.exist(item));
	}

	/*
	 * I creating and deleting the sme author in the datbase
	 */
	@Test
	@Transactional
	public void testDelete() {
		Book_Authors item = new Book_Authors();
		item.setAuthorId(2);
		item.setBookId(3);
		abdao.add(item);
		abdao.delete(item);
		assertNull(abdao.exist(item));

	}

	@Test
	@Transactional
	public void updateBooksofAuthor() {
		Author author = new Author();
		author.setAuthorId(1);
		int[] bookIds = new int[] { 1, 2, 3 };

		abdao.updateBooksofAuthor(author, bookIds);
		List<Book_Authors> booksAuthors = abdao.getByAuthorId(1);
		assertEquals("the size is not equal", 3, booksAuthors.size());
		for (int i = 0; i < 3; i++)
			assertEquals("the id for that book is not matching", i + 1, booksAuthors.get(i).getBookId());
	}

	@Test
	@Transactional
	public void updateAuthorsOfBook() {
		Book book = new Book();
		book.setBookId(1);
		int[] authorsIds = new int[] { 1, 2, 3 };

		abdao.updateAuthorsofBook(authorsIds, book);
		List<Book_Authors> booksAuthors = abdao.getByBookId(1);
		assertEquals("the size is not equal", 3, booksAuthors.size());
		for (int i = 0; i < 3; i++)
			assertEquals("the id for that author is not matching", i + 1, booksAuthors.get(i).getAuthorId());

	}

	@Test
	@Transactional
	public void deleteBooksByAuthorId() {
		abdao.deleteBooksOfAuthor(1);
		assertTrue(abdao.getByAuthorId(1).isEmpty());
	}

	@Test
	@Transactional
	public void deleteAuthorsByBookId() {
		abdao.deleteAuthorsOfBooks(1);
		assertTrue(abdao.getByBookId(1).isEmpty());
	}
}
