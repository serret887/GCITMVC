package com.gcit.training.lms.dao;

import static org.junit.Assert.*;

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
import com.gcit.training.spring.lms.dao.genre.GenreDAO;
import com.gcit.training.spring.lms.entity.Author;
import com.gcit.training.spring.lms.entity.Genre;
import com.gcit.training.spring.lms.entity.Genre;

@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { LMSConfig.class })
@ActiveProfiles("dev")
public class GenreDAOTest {
	@Autowired
	GenreDAO gdao;

	@Test
	public void autowired() {
		assertNotNull(gdao);
	}

	@Test
	public void testReadAll() {
		List<Genre> genres = gdao.readAll(1);
		assertEquals("the amount of genre is incorrect", 7, genres.size());
		assertNotNull("there is no books", genres.get(0).getBooks());
	}

	@Test
	public void testReadFirstLevel() {
		List<Genre> genres = gdao.readFirstLevel(1);
		assertEquals("the amount of genre is incorrect", 7, genres.size());
		assertNull("there are books", genres.get(0).getBooks());
	}

	@Test
	@Transactional
	public void testAdd() {
		Genre item = new Genre();
		item.setGenreName("robert");
		int id = gdao.add(item);
		assertEquals("mismatch id ", 8, id);
		assertEquals("The amount of the genres are not equals", 8, gdao.readAll(1).size());
	}

	@Test
	public void testGetById() {
		Genre author = gdao.getById(1);
		assertEquals("the name of the author", "Science fiction", author.getGenreName());

	}

	@Test
	public void testSearchByName() {
		List<Genre> genres = gdao.searchByName("Science fiction", 1);
		assertEquals("The query should return only 1", 1, genres.size());
		assertEquals("The name of the genre are not equals", genres.get(0).getGenreName(), "Science fiction");
		assertEquals("The id of the genre are not equals", 1, genres.get(0).getGenreId());
	}

	@Test
	@Transactional
	public void testDelete() {
		Genre item = new Genre();
		item.setGenreId(1);
		gdao.delete(item);
		assertEquals("The amount of the genres are not equals", 6, gdao.readAll(1).size());
	}

	@Test
	@Transactional
	public void testUpdate() {
		Genre item = new Genre();
		item.setGenreId(1);
		item.setGenreName("robert");
		gdao.update(item);
		assertEquals("The amount of the genres are not equals", 7, gdao.readAll(1).size());
		assertEquals("the name of the genre", "robert", gdao.getById(1).getGenreName());
	}

}
