package com.gcit.training.lms.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

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
import com.gcit.training.spring.lms.dao.Book_GenreDAO;
import com.gcit.training.spring.lms.entity.Book_Genre;

@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { LMSConfig.class })
@ActiveProfiles("dev")
public class Book_GenreDAOTest {

	@Autowired
	Book_GenreDAO bgdao;

	@Test
	@Transactional
	public void testAddGenresForBook() throws SQLException {
		bgdao.addGenresForBook(10, new int []{1,2,3,4});
		assertEquals("the amount does not match", 10, bgdao.readAll().size());

	}
	
	@Test
	@Transactional
	public void testAddBook_Genre() throws SQLException {
		Book_Genre item = new Book_Genre();
		item.setGenre_id(2);
		item.setBookId(3);
		bgdao.add(item);
		assertNotNull(bgdao.exist(item));
	}

	@Test
	public void getByBook() {
		Book_Genre item = new Book_Genre();
		item.setBookId(1);

		List<Book_Genre> genres = bgdao.getbyBook(item);
		assertEquals("mismatching size", 2, genres.size());
	}

	@Test
	public void getByGenre() {
		Book_Genre item = new Book_Genre();
		item.setGenre_id(1);
		List<Book_Genre> genres = bgdao.getbyGenre(item);
		assertEquals("mismatching size", 1, genres.size());
	}

	@Test
	@Transactional
	public void deleteBooksByGenre() throws SQLException {
		Book_Genre item = new Book_Genre();
		item.setGenre_id(2);
		item.setBookId(3);
		bgdao.add(item);
		bgdao.deleteBooksByGenre(item.getGenre_id());
		assertNull(bgdao.getbyGenre(item));
	}

	@Test
	@Transactional
	public void deleteGenresByBook() throws SQLException {
		Book_Genre item = new Book_Genre();
		item.setGenre_id(2);
		item.setBookId(3);
		bgdao.add(item);
		bgdao.deleteGenresByBook(item.getBookId());
		assertNull(bgdao.getbyBook(item));
	}

	@Test
	public void testReadAll() throws SQLException {

		List<Book_Genre> ba = bgdao.readAll();
		assertEquals("the amount does not match", 6, ba.size());

	}

}
