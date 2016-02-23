package com.gcit.training.spring.lms.service.entities;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.gcit.training.spring.lms.dao.Book_AuthorsDAO;
import com.gcit.training.spring.lms.dao.Book_CopiesDAO;
import com.gcit.training.spring.lms.dao.Book_GenreDAO;
import com.gcit.training.spring.lms.dao.book.BookDAO;
import com.gcit.training.spring.lms.dao.bookLoans.BookLoansDAO;
import com.gcit.training.spring.lms.entity.Book;

public class BookService {

	@Autowired
	BookDAO bookDAO;

	@Autowired
	Book_AuthorsDAO bookAuthorDao;

	@Autowired
	BookLoansDAO bookLoans;

	@Autowired
	Book_CopiesDAO bookCopiesDao;

	@Autowired
	Book_GenreDAO bookGenreDao;

	@Autowired
	LibraryService branchService;

	// ////////////////////////////////// books///////////////////////////
	public int addBook(Book book, int[] authorIds, int[] genreIds, int[] branchIds, Integer amount) {
		int bookId = bookDAO.add(book);
		// updating the table books_authors
		bookAuthorDao.addAuthorsForBook(bookId, authorIds);
		// updating the table bookGener
		bookGenreDao.addGenresForBook(bookId, genreIds);
		// inserting the branch amount
		branchService.addBookInBranches(branchIds, bookId, amount);
		return bookId;
	}

	public List<Book> readBooks(int pageNo) {
		return bookDAO.readAll(pageNo);
	}
	
	public List<Book> searchBookByTitle(String searchString,int pageNo){
		return bookDAO.searchByName(searchString, pageNo);
	}

	public List<Book> getBooksOwnByCardNo(int cardNo) throws SQLException{
		return bookDAO.getBooksOwnForCardNo(cardNo);
	}
	
	public List<Book> getBookAvailablesForBranch(int branchId,int cardNo) throws SQLException{
		return bookDAO.getBooksAvailableInLibraryForCardNo(branchId, cardNo);
	}

	public void editBook(Book book, int[] authorIds, int[] genreIds) throws SQLException {
		// cahnge authors
				bookAuthorDao.updateAuthorsofBook(authorIds, book);
				// change genre
				bookGenreDao.updateGenresForBook(genreIds, book);
		bookDAO.update(book);
		
	}

	public void deleteBook(Book book) throws SQLException {
		// delete relation with book loans table
		bookLoans.deleteByBook(book.getBookId());
		// delete relation with tbl books copies
		bookCopiesDao.deleteByBook(book.getBookId());
		// delete relation with genre
		bookGenreDao.deleteGenresByBook(book.getBookId());
		// delete relation with authors
		bookAuthorDao.deleteAuthorsOfBooks(book.getBookId());
		// delete book from book table
		bookDAO.delete(book);
	}

	public void deleteBooksByPublisher(int pubId) throws SQLException {
		List<Book> books = bookDAO.getBooksByPublisher(pubId);
		for (Book b : books) {
			deleteBook(b);
		}
	}

	public Book getBookById(int id) {
		return bookDAO.getById(id);
	}

	public List<Book> readFirstInfo(int i) {
		return bookDAO.readFirstLevel(i);
	}

}