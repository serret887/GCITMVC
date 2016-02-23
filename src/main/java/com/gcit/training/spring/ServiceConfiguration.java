package com.gcit.training.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gcit.training.spring.lms.service.entities.AuthorService;
import com.gcit.training.spring.lms.service.entities.BookLoansService;
import com.gcit.training.spring.lms.service.entities.BookService;
import com.gcit.training.spring.lms.service.entities.BorrowerService;
import com.gcit.training.spring.lms.service.entities.GenreService;
import com.gcit.training.spring.lms.service.entities.LibraryService;
import com.gcit.training.spring.lms.service.entities.PublisherService;

@Configuration
public class ServiceConfiguration {
	@Bean(name = "authorService")
	public AuthorService authorService() {
		AuthorService service = new AuthorService();
		return service;
	}

	@Bean(name = "publisherService")
	public PublisherService publisherService() {
		PublisherService service = new PublisherService();
		return service;
	}

	@Bean(name = "borrowerService")
	public BorrowerService borrowerService() {
		return new BorrowerService();
	}

	@Bean(name = "bookService")
	public BookService bookService() {
		return new BookService();
	}
	@Bean(name = "genreService")
	public GenreService genreService() {
		return new GenreService();
	}
	@Bean(name = "libraryService")
	public LibraryService libraryService() {
		return new LibraryService();
	}
	@Bean(name = "bookLoansService")
	public BookLoansService bookLoansService() {
		return new BookLoansService();
	}

}
