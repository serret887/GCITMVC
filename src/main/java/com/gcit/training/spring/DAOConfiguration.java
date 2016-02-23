package com.gcit.training.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gcit.training.spring.lms.dao.Book_AuthorsDAO;
import com.gcit.training.spring.lms.dao.Book_CopiesDAO;
import com.gcit.training.spring.lms.dao.Book_GenreDAO;
import com.gcit.training.spring.lms.dao.BorrowerDAO;
import com.gcit.training.spring.lms.dao.LibraryDAO;
import com.gcit.training.spring.lms.dao.author.AuthorDAO;
import com.gcit.training.spring.lms.dao.author.ExtractFirstDataAuthor;
import com.gcit.training.spring.lms.dao.author.ExtractFullDataAuthor;
import com.gcit.training.spring.lms.dao.book.BookDAO;
import com.gcit.training.spring.lms.dao.book.ExtractFirstDataBook;
import com.gcit.training.spring.lms.dao.book.ExtractFullDataBook;
import com.gcit.training.spring.lms.dao.bookLoans.BookLoansDAO;
import com.gcit.training.spring.lms.dao.bookLoans.ExtractFirstDataBookLoans;
import com.gcit.training.spring.lms.dao.bookLoans.ExtractFullDataBookLoans;
import com.gcit.training.spring.lms.dao.genre.ExtractFirstDataGenre;
import com.gcit.training.spring.lms.dao.genre.ExtractFullDataGenre;
import com.gcit.training.spring.lms.dao.genre.GenreDAO;
import com.gcit.training.spring.lms.dao.publisher.ExtractFirstDataPublisher;
import com.gcit.training.spring.lms.dao.publisher.ExtractFullDataPublisher;
import com.gcit.training.spring.lms.dao.publisher.PublisherDAO;

@Configuration
public class DAOConfiguration {

	/////////////////////////// book//////////////
	@Bean
	public BookDAO bookDAO() {
		return new BookDAO();
	}

	@Bean
	public ExtractFullDataBook extractFullDataBook() {
		return new ExtractFullDataBook();
	}

	@Bean
	public ExtractFirstDataBook extractFirstDataBook() {
		return new ExtractFirstDataBook();
	}

	////////////////////////////// author

	@Bean
	public AuthorDAO authorDAO() {
		return new AuthorDAO();
	}

	@Bean
	public ExtractFirstDataAuthor extractFirstDataAuthor() {
		return new ExtractFirstDataAuthor();
	}

	@Bean
	public ExtractFullDataAuthor extractFullDataAuthor() {
		return new ExtractFullDataAuthor();
	}

	///////////////////// publisher///////////
	@Bean
	public PublisherDAO PublisherDAO() {
		return new PublisherDAO();
	}

	@Bean
	public ExtractFirstDataPublisher extractFirstDataPublisher() {
		return new ExtractFirstDataPublisher();
	}

	@Bean
	public ExtractFullDataPublisher extractFullDataPublisher() {
		return new ExtractFullDataPublisher();
	}

	/////////////////// genre///////////////
	@Bean
	public GenreDAO GenreDAO() {
		return new GenreDAO();
	}

	@Bean
	public ExtractFirstDataGenre extractFirstDataGenre() {
		return new ExtractFirstDataGenre();
	}

	@Bean
	public ExtractFullDataGenre extractFullDataGenre() {
		return new ExtractFullDataGenre();
	}

	///////////////////////// mix tables
	@Bean
	public Book_GenreDAO bookGenre_DAO() {
		return new Book_GenreDAO();
	}

	@Bean
	public Book_AuthorsDAO bookAuthor_DAO() {
		return new Book_AuthorsDAO();
	}

	@Bean
	public Book_CopiesDAO bookCopiesDAO() {
		return new Book_CopiesDAO();
	}

	////////////////////// library
	@Bean
	public LibraryDAO libraryDAO() {
		return new LibraryDAO();
	}

	/////////////////////////////// book loans

	@Bean
	public BookLoansDAO bookLoansDAO() {
		return new BookLoansDAO();
	}

	@Bean
	public ExtractFirstDataBookLoans extractFirstDataBookLoans() {
		return new ExtractFirstDataBookLoans();
	}

	@Bean
	public ExtractFullDataBookLoans extractFullDataBookLoans() {
		return new ExtractFullDataBookLoans();
	}
	///////////////// borroewr

	@Bean
	public BorrowerDAO borrowerDAO() {
		return new BorrowerDAO();
	}

}
