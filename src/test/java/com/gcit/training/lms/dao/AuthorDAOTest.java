package com.gcit.training.lms.dao;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.gcit.training.spring.LMSConfig;
import com.gcit.training.spring.lms.dao.author.AuthorDAO;
import com.gcit.training.spring.lms.entity.Author;


@TransactionConfiguration(transactionManager="transactionManager" ,defaultRollback =true)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {LMSConfig.class})
@ActiveProfiles("dev")
public class AuthorDAOTest {


	@Autowired
	AuthorDAO adao;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
/**
 * return the list of all authors
 */
	@Test
	public void testReadAll_first_level() {
		List<Author> authors = adao.readFirstLevel(1);
		assertEquals("the amount of authors", 3, authors.size());
		assertNull("there is books", authors.get(0).getBooks());
	}

	@Test
	public void testReadAll(){
		List<Author> authors = adao.readAll(1);
		assertEquals("the amount of authors", 3, authors.size());
		assertNotNull("there is no books", authors.get(0).getBooks());
		
	}
	/*
	 * testing that I am retrieving the right amount of data when I am seching
	 * for an specific author's
	 */
	@Test
	public void testSearchByName() {
		List<Author> authors = adao.searchByName("Cathryn Bogan", 1);
		assertEquals("The query should return only 1", 1, authors.size());
		assertEquals("The name of the author are not equals", authors.get(0)
				.getAuthorName(), "Cathryn Bogan");
		assertEquals("The id of the author are not equals", 1, authors.get(0)
				.getAuthorId());

	}

	/*
	 * testing tha t add is returning the right id
	 */
	@Test
	@Transactional
	public void testAdd() {
		Author item = new Author();
		item.setAuthorName("robert");
		int id = adao.add(item);
		assertEquals("mismatch id ", 4, id);
		assertEquals("The amount of the authors are not equals", 4, adao
				.readAll(1).size());
	}

	/*
	 * I am deleting the first author in the database
	 */
	@Test
	@Transactional
	public void testDelete() {
		Author item = new Author();
		item.setAuthorId(1);
		adao.delete(item);
		assertEquals("The amount of the authors are not equals", 2, adao
				.readAll(1).size());
	}


	/*
	 * I am getting an author by his ID
	 */
	@Test
	public void testGetById() {
		Author author = adao.getById(1);
		assertEquals("the name of the author", author.getAuthorName(),
				"Cathryn Bogan");
	}

	/*
	 * I am updating an author name by id 
	 */
	@Test
	@Transactional
	public void testUpdate() {
		Author item = new Author();
		item.setAuthorId(1);
		item.setAuthorName("robert");
		adao.update(item);
		assertEquals("The amount of the authors are not equals", 3, adao
				.readAll(1).size());
		assertEquals("the name of the author", "robert", adao.getById(1)
				.getAuthorName());
	}

	// @Test
	// public void testExtractData() {
	// fail("Not yet implemented");
	// }

}
