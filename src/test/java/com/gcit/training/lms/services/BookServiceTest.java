package com.gcit.training.lms.services;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.gcit.training.spring.lms.dao.Book_AuthorsDAO;
import com.gcit.training.spring.lms.dao.author.AuthorDAO;
import com.gcit.training.spring.lms.dao.book.BookDAO;
import com.gcit.training.spring.lms.entity.Book;
import com.gcit.training.spring.lms.service.entities.AuthorService;
import com.gcit.training.spring.lms.service.entities.BookService;

public class BookServiceTest {

	@InjectMocks
	private BookService bookService;

	@Mock
	private BookDAO bookDao;

	@Mock
	private Book_AuthorsDAO bookAuthorDao;

	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddBook() {
		Book book = new Book();
		int[] authorIds = { 1, 2, 3 };
		int[] genreIds = { 1, 2 };
		int[] branchIds = { 1, 2 };
		int amount = 4;
		int bookId = bookService.addBook(book, authorIds, genreIds, branchIds, amount);
		verify(bookDao, times(1)).add(book);
		verify(bookAuthorDao, times(1)).addAuthorsForBook(bookId, authorIds);
	}

	
	
	
	@Test
	public void testReadBooks() {
		bookService.readBooks(1);
		verify(bookDao, times(1)).readAll(1);

	}
}
