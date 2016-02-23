package com.gcit.training.lms.services;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.gcit.training.spring.LMSConfig;
import com.gcit.training.spring.lms.dao.Book_AuthorsDAO;
import com.gcit.training.spring.lms.dao.publisher.PublisherDAO;
import com.gcit.training.spring.lms.entity.Publisher;
import com.gcit.training.spring.lms.service.entities.BookService;
import com.gcit.training.spring.lms.service.entities.PublisherService;

@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { LMSConfig.class })
@ActiveProfiles("dev")
public class PublisherServiceTest {

	@InjectMocks
	private PublisherService publisherService;

	@Mock
	private PublisherDAO publisherDAO;

	@Mock
	private BookService bookService;
	
	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void authowiredIsWorking() {
		assertNotNull(publisherService);
	}

	@Test
	public void testAddPublisher() {
		Publisher pub = new Publisher();
		publisherService.addPublisher(pub);
		verify(publisherDAO, times(1)).add(pub);
	}

	@Test
	public void testReadPublisherFirstInfo() {
		publisherService.readPublisherFirstInfo(1);
		verify(publisherDAO, times(1)).readFirstLevel(1);
	}

	@Test
	public void testReadPublishers() {
		publisherService.readPublishers(1);
		verify(publisherDAO, times(1)).readAll(1);
	}

	@Test
	public void testEditPublisher() {
		Publisher publisher = new Publisher();
		publisherService.editPublisher(publisher);
		verify(publisherDAO, times(1)).update(publisher);
	}

	@Test
	public void testDeletePublisher() throws SQLException {
		Publisher pub = new Publisher();
		pub.setPublisherId(1);
		publisherService.deletePublisher(pub);
		verify(publisherDAO, times(1)).delete(pub);
		
//		verify(bookService,times(1)).deleteBook(null);

	}

	@Test
	public void testGetPublisherById() {
		publisherService.getPublisherById(1);
		verify(publisherDAO, times(1)).getById(1);

	}

}
