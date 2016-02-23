package com.gcit.training.spring.lms.service.entities;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.gcit.training.spring.lms.dao.publisher.PublisherDAO;
import com.gcit.training.spring.lms.entity.Author;
import com.gcit.training.spring.lms.entity.Publisher;

public class PublisherService {
	@Autowired
	PublisherDAO publisherDAO;
	@Autowired
	BookService bookService;

	public int addPublisher(Publisher publisher) {
		return publisherDAO.add(publisher);
	}

	public List<Publisher> readPublishers(int pageNo) {
		return publisherDAO.readAll(pageNo);
	}

	public void editPublisher(Publisher publisher) {
		publisherDAO.update(publisher);
	}

	public void deletePublisher(Publisher publisher) throws SQLException {
		bookService.deleteBooksByPublisher(publisher.getPublisherId());
		publisherDAO.delete(publisher);
	}

	public Publisher getPublisherById(int id) {
		return publisherDAO.getById(id);
	}

	public List<Publisher> readPublisherFirstInfo(int i) {
		return publisherDAO.readFirstLevel(i);

	}
}
