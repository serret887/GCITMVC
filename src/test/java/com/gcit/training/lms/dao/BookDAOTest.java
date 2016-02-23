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
import com.gcit.training.spring.lms.dao.book.BookDAO;
import com.gcit.training.spring.lms.entity.Author;
import com.gcit.training.spring.lms.entity.Book;
import com.gcit.training.spring.lms.entity.Publisher;

@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { LMSConfig.class })
@ActiveProfiles("dev")
public class BookDAOTest {

	@Autowired
	BookDAO bdao;

	@Test
	public void testGetBooksByBranchId() throws SQLException {
		List<Book> books = bdao.getBooksByBranchId(1);
		assertEquals("the amount of books is incorrect", 3, books.size());
	}

	@Test
	@Transactional
	public void testDeleteBook() {
		Book item = new Book();
		item.setBookId(1);
		bdao.delete(item);
		assertNull("the book exist", bdao.getById(1));
	}

	@Test
	@Transactional
	public void testUpdateBook() {
		Book item = new Book();
		item.setBookId(1);
		item.setTitle("alex");
		Publisher publisher = new Publisher();
		publisher.setPublisherId(1);
		item.setPublisher(publisher);
		bdao.update(item);
		Book actual = bdao.getById(1);
		assertNotNull("the book not exist", actual);
		assertEquals("the book title not match", "alex", actual.getTitle());
		assertEquals("the book publishernot match", 1, actual.getPublisher().getPublisherId());
	}

	@Test
	public void testGetBooksAvailableInLibraryForCardNo() throws SQLException {
		List<Book> bAvailable = bdao.getBooksAvailableInLibraryForCardNo(1, 1);
		assertEquals("the amount of book return is not correct", 2, bAvailable.size());

	}

	@Test
	public void testGetBooksOwnForCardNo() throws SQLException {
		List<Book> bAvailable = bdao.getBooksOwnForCardNo(1);
		assertEquals("the amount of book return is not correct", 3, bAvailable.size());

	}

	@Test
	public void testReadAll() {
		List<Book> books = bdao.readAll(1);
		assertEquals("the amount of authors", 3, books.size());
		assertNotNull("there is no authors", books.get(0).getAuthors());
		assertNotNull("there is no genre", books.get(0).getGenre());
		assertNotNull("there is no publisher", books.get(0).getPublisher());
	}

	@Test
	public void testReadFirstLevel() {
		List<Book> books = bdao.readFirstLevel(1);
		assertEquals("the amount of authors", 3, books.size());
		assertNull("there is authors", books.get(0).getAuthors());
		assertNull("there is genre", books.get(0).getGenre());
		assertNull("there is publisher", books.get(0).getPublisher());
	}

	@Test
	@Transactional
	public void testAdd() {
		Book item = new Book();
		item.setTitle("alex");
		Publisher publisher = new Publisher();
		publisher.setPublisherId(1);
		item.setPublisher(publisher);

		int bid = bdao.add(item);
		Book actual = bdao.getById(bid);
		assertNotNull("the book exist", actual);
		assertEquals("the book title not match", "alex", actual.getTitle());
		assertEquals("the book publishernot match", 1, actual.getPublisher().getPublisherId());
	}

	@Test
	public void testSearchByName() {
		List<Book> b = bdao.searchByName("Administaff ALEX", 1);
		assertEquals("the amount of book return is not correct", 1, b.size());

	}

	@Test
	public void testGetById() {
		Book b = bdao.getById(1);
		assertEquals("the book title is not correct", "Administaff ALEX", b.getTitle());

	}

}
