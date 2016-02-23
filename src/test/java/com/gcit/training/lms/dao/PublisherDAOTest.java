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
import com.gcit.training.spring.lms.dao.publisher.PublisherDAO;
import com.gcit.training.spring.lms.entity.Book;
import com.gcit.training.spring.lms.entity.Publisher;

@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { LMSConfig.class })
@ActiveProfiles("dev")
public class PublisherDAOTest {

	@Autowired
	PublisherDAO publisherDAO;

	@Test
	public void autowiredWorking() {
		assertNotNull(publisherDAO);
	}

	/*
	 * this test enforce teh publisher to return all his books
	 */
	@Test
	public void testReadAll() {
		List<Publisher> publisher = publisherDAO.readAll(1);
		assertEquals("the publisher list size is incorrect", 2, publisher.size());
		assertEquals("the pub 1 does not have that book size", 1, publisher.get(0).getBooks().size());
		assertEquals("the pub 2 does not have that book size", 2, publisher.get(1).getBooks().size());
	}

	@Test
	public void testReadFirstLevel() {
		List<Publisher> publisher = publisherDAO.readFirstLevel(1);
		assertEquals("the publisher list size is incorrect", 2, publisher.size());
		assertNull("it does not have any book data", publisher.get(0).getBooks());
		assertNull("it does not have any book data", publisher.get(1).getBooks());
	}

	@Test
	@Transactional
	public void testAdd_it_add_a_new_item() {
		Publisher publisher = new Publisher();
		publisher.setPublisherAddress("aqui");
		publisher.setPublisherName("test");
		publisher.setPublisherPhone(String.valueOf(1));
		System.out.println(publisherDAO.readFirstLevel(1).size());
		int pubId = publisherDAO.add(publisher);

		assertEquals("the pub id is not correct", 3, pubId);
		assertEquals("the amount of publisher should be incremented", 3, publisherDAO.readFirstLevel(1).size());
	}

	@Test
	@Transactional
	public void testAdd_it_add_correct_item() {
		Publisher publisher = new Publisher();
		publisher.setPublisherAddress("aqui");
		publisher.setPublisherName("test");
		publisher.setPublisherPhone(String.valueOf(1));
		int pubId = publisherDAO.add(publisher);
		assertEquals("the new publisher name should match ", "test", publisherDAO.getById(pubId).getPublisherName());
		assertEquals("the new publisher address should match ", "aqui",
				publisherDAO.getById(pubId).getPublisherAddress());
		assertEquals("the new publisher phone should match ", "1", publisherDAO.getById(pubId).getPublisherPhone());
	}

	@Test
	public void testGetById() {
		assertEquals("the publisher name is incorrect", "Gillette Company", publisherDAO.getById(1).getPublisherName());

	}

	@Test
	public void testSearchByName() {
		List<Publisher> publisher = publisherDAO.searchByName("Gillette Company", 1);
		assertEquals("the size of publisher search is incorrect", 1, publisher.size());
		assertEquals("the name of publisher search is incorrect", "Gillette Company",
				publisher.get(0).getPublisherName());

	}

	@Test
	@Transactional
	public void testDelete() {
		Publisher item = new Publisher();
		item.setPublisherId(1);
		publisherDAO.delete(item);
		assertEquals("the amount of publisher should be decremented", 1, publisherDAO.readFirstLevel(1).size());
		assertNull("the publisher is not found any more", publisherDAO.getById(1));
	}

	@Test
	@Transactional
	public void testUpdate_change_the_values() {
		Publisher publisher = new Publisher();
		publisher.setPublisherId(1);
		publisher.setPublisherAddress("aqui");
		publisher.setPublisherName("test");
		publisher.setPublisherPhone(String.valueOf(1));
		publisherDAO.update(publisher);
		
		assertEquals("the new publisher name should match ", "test", publisherDAO.getById(1).getPublisherName());
		assertEquals("the new publisher address should match ", "aqui",
				publisherDAO.getById(1).getPublisherAddress());
		assertEquals("the new publisher phone should match ", "1", publisherDAO.getById(1).getPublisherPhone());

	
	}

}
