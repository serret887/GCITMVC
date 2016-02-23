package com.gcit.training.spring.lms.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.gcit.training.spring.lms.entity.Author;
import com.gcit.training.spring.lms.entity.Book;
import com.gcit.training.spring.lms.entity.Borrower;
import com.gcit.training.spring.lms.entity.Genre;
import com.gcit.training.spring.lms.entity.LibraryBranch;
import com.gcit.training.spring.lms.entity.Publisher;
import com.gcit.training.spring.lms.service.entities.AuthorService;
import com.gcit.training.spring.lms.service.entities.BookService;
import com.gcit.training.spring.lms.service.entities.BorrowerService;
import com.gcit.training.spring.lms.service.entities.GenreService;
import com.gcit.training.spring.lms.service.entities.LibraryService;
import com.gcit.training.spring.lms.service.entities.PublisherService;

public class AdminstratorProfile {
	@Autowired
	AuthorService authorService;
	@Autowired
	BorrowerService borrowerService;
	@Autowired
	BookService bookService;
	@Autowired
	GenreService genreService;
	@Autowired
	LibraryService libraryService;
	@Autowired
	PublisherService pubService;

	//////////////////////////////////// AUTHORS///////////////////////////
	@Transactional
	public void addAuthor(Author author) {
		authorService.addAuthor(author);
	}

	public List<Author> searchAuthorByName(String searchString, int pageNo){
		return authorService.searchAuthorByName(searchString,pageNo);
	}
	
	public Author getAuthorById(int id) {
		return authorService.getAuthorById(id);
	}

	public List<Author> getAuthorsFullData(int pageNo) {
		return authorService.readAuthors(pageNo);
	}

	public List<Author> getAuthorsSimpleData(int pageNo) {
		return authorService.readFirstInfo(pageNo);
	}

	@Transactional
	public void editAuthor(Author author, int[] bookIds) {
		authorService.editAuthor(author, bookIds);
	}

	@Transactional
	public void deleteAuthor(Author author) {
		authorService.deleteAuthor(author);
	}

	////////////////////////////////////// BOOKS///////////////////////////
	public List<Book> getBooksFullData(int pageNo) {
		return bookService.readBooks(pageNo);
	}
	public Book getBookById(int id) {
		return bookService.getBookById(id);
	}
	
	public List<Book> searchBookByTitle(String searchString, int pageNo){
		return bookService.searchBookByTitle(searchString,pageNo);
	}
	
	
	@Transactional
	public void addBook(Book book, int[] authorIds, int[] genreIds, Integer amount, int[] branchIds) {
		bookService.addBook(book, authorIds, genreIds, branchIds, amount);
	}

	@Transactional
	public void editBook(Book book, int[] authorIds, int[] genreIds) throws SQLException {
		bookService.editBook(book, authorIds, genreIds);
	}

	@Transactional
	public void deleteBook(Book book) throws SQLException {
		bookService.deleteBook(book);
	}

	//////////////////////////////////// PUBLISHER///////////////////////////
	@Transactional
	public int addPulisher(Publisher publisher) {
		return pubService.addPublisher(publisher);
	}

	public List<Publisher> getPublishersFullData(int pageNo) {
		return pubService.readPublishers(pageNo);
	}

	public Publisher getPublisherById(int pubId) {
		return pubService.getPublisherById(pubId);
	}

	/*
	 * list of names
	 */
	public List<Publisher> getPublishers(int pageNo) {
		return pubService.readPublisherFirstInfo(pageNo);
	}

	@Transactional
	public void editPublisher(Publisher publisher) {
		pubService.editPublisher(publisher);
	}

	@Transactional
	public void deletePulbisher(Publisher publisher) throws SQLException {
		pubService.deletePublisher(publisher);
	}

	//////////////////////////////////// BORROWER///////////////////////////
	@Transactional
	public void addBorrower(Borrower borrower) {
		borrowerService.addBorrower(borrower);

	}

	public List<Borrower> getBorrowersFullData(int pageNo) {
		return borrowerService.readBorrower(pageNo);
	}

	public List<Borrower> getBorrowers(int pageNo) {
		return borrowerService.readFirstInfo(pageNo);
	}

	@Transactional
	public void editBorrower(Borrower borrower) {
		borrowerService.editBorrower(borrower);
	}

	@Transactional
	public void deleteBorrower(Borrower borrower) {
		borrowerService.deleteBorrower(borrower);
	}

	//////////////////////////////////// GENRES///////////////////////////

	public List<Genre> getGenresFullData(int pageNo) {
		return genreService.readGenre(pageNo);
	}

	public List<Genre> getGenres(int pageNo) {
		return genreService.readFirstInfo(pageNo);
	}

	@Transactional
	public void addGenre(Genre genre) {
		genreService.addGenre(genre);
	}

	@Transactional
	public void editGenre(Genre genre) {
		genreService.editGenre(genre);
	}

	@Transactional
	public void deleteGenre(Genre genre) throws SQLException {
		genreService.deleteGenre(genre);
	}

	/////////////////////////////////// LIBRARY-BRANCH////////////////////////
	@Transactional
	public void addLibrary(LibraryBranch library) {
		libraryService.addLibraryBranch(library);
	}

	public LibraryBranch getLibraryById(int branchId) {
		return libraryService.getLibraryBranchById(branchId);
	}

	public List<LibraryBranch> getLibraries(int pageNo) {
		return libraryService.readLibraries(pageNo);
	}

	public List<LibraryBranch> getLibrariesFullData(int pageNo) {
		return libraryService.readLibraries(pageNo);
	}

	@Transactional
	public void editLibrary(LibraryBranch library) {
		libraryService.editLibraryBranch(library);
	}

	public void deleteLibrary(LibraryBranch library) throws SQLException {
		libraryService.deleteLibraryBranch(library);
	}
}
