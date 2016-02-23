package com.gcit.training.spring.lms;

import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gcit.training.spring.lms.entity.Author;
import com.gcit.training.spring.lms.entity.Book;
import com.gcit.training.spring.lms.entity.Borrower;
import com.gcit.training.spring.lms.entity.LibraryBranch;
import com.gcit.training.spring.lms.entity.Publisher;
import com.gcit.training.spring.lms.service.AdminstratorProfile;
import com.gcit.training.spring.lms.service.entities.AuthorService;

/**
 * Handles requests for the application home page.
 */

@Controller
@RequestMapping("/Administrator")
public class AdministratorController {

	private static final Logger logger = LoggerFactory.getLogger(AdministratorController.class);

	@Autowired
	private AdminstratorProfile admin;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		return "admin";
	}
	////////////////////////////////////////// BOOKS/////////////////////////////

	@RequestMapping(value = "/ViewBooks", method = RequestMethod.GET)
	public String viewBooks(Model model) throws SQLException {
		logger.info("hitting the viewBooks");
		return "Administrator/Book/viewBooks";
	}

	@RequestMapping(value = "/DeleteBook", method = RequestMethod.POST)
	public String viewBooks(@RequestParam("bookId") int bookId, Model model) throws SQLException {
		Book book = new Book();
		book.setBookId(bookId);
		admin.deleteBook(book);
		return "Administrator/Book/viewBooks";
	}

	@RequestMapping(value = "/EditBook", method = RequestMethod.POST)
	public String viewBooks(@RequestParam("pubId") int pubId, @RequestParam("authorIds") int[] authorIds,
			@RequestParam("generIds") int[] genreIds, @RequestParam("bookId") int bookId,
			@RequestParam("name") String title, Model model) throws SQLException {
		model.addAttribute("pageNo", 1);

		Book book = new Book();
		book.setBookId(bookId);
		book.setTitle(title);

		Publisher publisher = new Publisher();
		publisher.setPublisherId(pubId);
		book.setPublisher(publisher);

		admin.editBook(book, authorIds, genreIds);
		return "Administrator/Book/viewBooks";
	}

	@RequestMapping(value = "/EditBook", method = RequestMethod.GET)
	public String viewGetBooks(@RequestParam("bookId") int bookId, Model model) throws SQLException {
		return "Administrator/Book/editBook";
	}

	@RequestMapping(value = "/AddBook", method = RequestMethod.GET)
	public String addGetBooks() throws SQLException {
		return "Administrator/Book/addBook";
	}

	@RequestMapping(value = "/AddBook", method = RequestMethod.POST)
	public String addPostBooks(@RequestParam("pubId") int pubId, @RequestParam("authorIds") int[] authorIds,
			@RequestParam("generIds") int[] genreIds, @RequestParam("amount") int amount,
			@RequestParam("bookTitle") String title, @RequestParam("branchIds") int[] branchIds, Model model)
					throws SQLException {
		Book book = new Book();
		book.setTitle(title);
		Publisher publisher = new Publisher();
		publisher.setPublisherId(pubId);
		book.setPublisher(publisher);
		model.addAttribute("pageNo", 1);
		admin.addBook(book, authorIds, genreIds, amount, branchIds);
		return "Administrator/Book/viewBooks";
	}

	/////////////////////// BORROWER
	@RequestMapping(value = "/ViewBorrowers", method = RequestMethod.GET)
	public String viewBorrowers(@RequestParam("pageNo") int pageNo, Model model) throws SQLException {
		model.addAttribute("pageNo", pageNo);
		return "Administrator/Borrower/viewBorrowers";
	}

	@RequestMapping(value = "/EditBorrower", method = RequestMethod.GET)
	public String editGetBorrowers() throws SQLException {
		return "Administrator/Borrower/editBorrower";
	}

	@RequestMapping(value = "/EditBorrower", method = RequestMethod.POST)
	public String editPostBorrower(@RequestParam("name") String name, @RequestParam("cardNo") int cardNo,
			@RequestParam("phone") int phone, @RequestParam("address") String address, Model model)
					throws SQLException {
		Borrower borrower = new Borrower();
		borrower.setCarNo(cardNo);
		borrower.setAddress(address);
		borrower.setName(name);
		borrower.setPhone(phone);
		model.addAttribute("pageNo", 1);
		admin.editBorrower(borrower);
		return "Administrator/Borrower/viewBorrowers";
	}

	@RequestMapping(value = "/AddBorrower", method = RequestMethod.POST)
	public String addPostBorrower(@RequestParam("name") String name, @RequestParam("phone") int phone,
			@RequestParam("address") String address, Model model) throws SQLException {
		Borrower borrower = new Borrower();
		borrower.setAddress(address);
		borrower.setName(name);
		borrower.setPhone(phone);
		model.addAttribute("pageNo", 1);
		admin.addBorrower(borrower);
		return "Administrator/Borrower/viewBorrowers";
	}

	@RequestMapping(value = "/DeleteBorrower", method = RequestMethod.POST)
	public String deleteBorrower(@RequestParam("cardNo") int cardNo, Model model) throws SQLException {
		Borrower borrower = new Borrower();
		borrower.setCarNo(cardNo);
		model.addAttribute("pageNo", 1);
		admin.deleteBorrower(borrower);
		return "Administrator/Borrower/viewBorrowers";
	}

	/////////////////////////////////////////// libraries/////////////////////////////////
	@RequestMapping(value = "/ViewLibraries", method = RequestMethod.GET)
	public String viewLibraries(@RequestParam("pageNo") int pageNo, Model model) throws SQLException {
		model.addAttribute("pageNo", pageNo);
		return "Administrator/Library/viewLibraries";
	}

	@RequestMapping(value = "/EditLibrary", method = RequestMethod.POST)
	public String editPostLibary(@RequestParam("address") String libAddress, @RequestParam("branchId") int branchId,
			@RequestParam("name") String libName, Model model) throws SQLException {
		LibraryBranch library = new LibraryBranch();
		library.setBranchAddress(libAddress);
		library.setBranchName(libName);
		library.setBranchId(branchId);
		model.addAttribute("pageNo", 1);
		admin.editLibrary(library);
		logger.info("editing the library");
		return "Administrator/Library/viewLibraries";
	}

	@RequestMapping(value = "/EditLibrary", method = RequestMethod.GET)
	public String editGetLibrary(@RequestParam("branchId") int pubId, Model model) throws SQLException {
		logger.info("editting get library");
		return "Administrator/Library/editLibrary";
	}

	@RequestMapping(value = "/DeleteLibrary", method = RequestMethod.POST)
	public String deleteLibrary(@RequestParam("branchId") int branchId, Model model) throws SQLException {
		LibraryBranch branch = new LibraryBranch();
		branch.setBranchId(branchId);
		model.addAttribute("pageNo", 1);
		admin.deleteLibrary(branch);
		logger.debug("deleting a nw library");
		return "Administrator/Library/viewLibraries";
	}

	@RequestMapping(value = "/AddLibrary", method = RequestMethod.POST)
	public String addPostLibrary(@RequestParam("name") String branchName, @RequestParam("address") String branchAddress,
			Model model) throws SQLException {
		logger.debug("touching the add library");
		LibraryBranch branch = new LibraryBranch();
		branch.setBranchAddress(branchAddress);
		branch.setBranchName(branchName);
		admin.addLibrary(branch);
		model.addAttribute("pageNo", 1);
		logger.debug("adding library");
		return "Administrator/Library/viewLibraries";
	}

	/////////////////////////////////// authors
	@RequestMapping(value = "/ViewAuthors", method = RequestMethod.GET)
	public String viewAuthors(Model model) throws SQLException {
		return "Administrator/Author/viewAuthors";
	}

	@RequestMapping(value = "/DeleteAuthor", method = RequestMethod.POST)
	public String deleteAuthor(@RequestParam("authorId") int authorId, Model model) throws SQLException {
		Author author = new Author();
		author.setAuthorId(authorId);
		model.addAttribute("pageNo", 1);
		admin.deleteAuthor(author);
		logger.debug("deleting a nw autor");
		return "Administrator/Author/viewAuthors";
	}

	@RequestMapping(value = "/EditAuthor", method = RequestMethod.POST)
	public String editPostAuthor(@RequestParam("authorId") int authorId,@RequestParam("bookIds") int[] bookIds, @RequestParam("name") String authorName,
			Model model) throws SQLException {
		System.out.println("POST ALEX: " + authorName);
		
		Author author = new Author();
		author.setAuthorId(authorId);
		author.setAuthorName(authorName);
		admin.editAuthor(author, bookIds);
		model.addAttribute("pageNo", 1);
		logger.info("adding autor");
		return "Administrator/Author/viewAuthors";
	}

	@RequestMapping(value = "/EditAuthor", method = RequestMethod.GET)
	public String editGetAuthor(@RequestParam("authorId") int authorId, Model model) throws SQLException {
		logger.info("editting get autor");
		// System.out.println("ALEX: " + authorId);
		// model.addAttribute("authorId", authorId);
		return "Administrator/Author/editAuthor";
	}

	@RequestMapping(value = "/AddAuthor", method = RequestMethod.POST)
	public String addPostAuthor(@RequestParam("name") String authorName, Model model) throws SQLException {
		Author author = new Author();
		author.setAuthorName(authorName);
		admin.addAuthor(author);
		model.addAttribute("pageNo", 1);
		logger.info("adding autor");
		return "Administrator/Author/viewAuthors";
	}

	/////////////////////////////////// Publisher
	@RequestMapping(value = "/ViewPublishers", method = RequestMethod.GET)
	public String viewPublishers(@RequestParam("pageNo") int pageNo, Model model) throws SQLException {
		model.addAttribute("pageNo", pageNo);
		return "Administrator/Publisher/viewPublishers";
	}

	@RequestMapping(value = "/EditPublisher", method = RequestMethod.POST)
	public String editPostPublisher(@RequestParam("pubAddress") String pubAddress,
			@RequestParam("pubPhone") String pubPhone, @RequestParam("pubId") int pubId,
			@RequestParam("pubName") String pubName, Model model) throws SQLException {

		Publisher publisher = new Publisher();
		publisher.setPublisherId(pubId);
		publisher.setPublisherName(pubName);
		publisher.setPublisherAddress(pubAddress);
		publisher.setPublisherPhone(pubPhone);

		admin.editPublisher(publisher);

		model.addAttribute("pageNo", 1);

		logger.info("editing the publisher");
		return "Administrator/Publisher/viewPublishers";
	}

	@RequestMapping(value = "/EditPublisher", method = RequestMethod.GET)
	public String editGetPublisher(@RequestParam("pubId") int pubId, Model model) throws SQLException {
		logger.info("editting get autor");
		// System.out.println("ALEX: " + authorId);
		// model.addAttribute("authorId", authorId);
		return "Administrator/Publisher/editPublisher";
	}

	@RequestMapping(value = "/DeletePublisher", method = RequestMethod.POST)
	public String deletePublisher(@RequestParam("pubId") int publisherId, Model model) throws SQLException {
		Publisher publisher = new Publisher();
		publisher.setPublisherId(publisherId);
		model.addAttribute("pageNo", 1);
		admin.deletePulbisher(publisher);
		logger.debug("deleting a nw autor");
		return "Administrator/Publisher/viewPublishers";
	}

	@RequestMapping(value = "/AddPublisher", method = RequestMethod.POST)
	public String addPostPublihser(@RequestParam("address") String publisherAddress,
			@RequestParam("phone") String publisherPhone, @RequestParam("name") String publisherName, Model model)
					throws SQLException {
		Publisher publisher = new Publisher();
		publisher.setPublisherPhone(publisherPhone);
		publisher.setPublisherName(publisherName);
		publisher.setPublisherAddress(publisherAddress);
		admin.addPulisher(publisher);
		model.addAttribute("pageNo", 1);
		logger.info("adding autor");
		return "Administrator/Publisher/viewPublishers";
	}

}
