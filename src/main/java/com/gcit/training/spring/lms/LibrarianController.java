package com.gcit.training.spring.lms;

import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.gcit.training.spring.lms.entity.LibraryBranch;
import com.gcit.training.spring.lms.entity.Publisher;
import com.gcit.training.spring.lms.service.LibrarianProfile;
import com.gcit.training.spring.lms.service.entities.AuthorService;

@Controller
@RequestMapping(value = "/Librarian")
public class LibrarianController {

	private static final Logger logger = LoggerFactory.getLogger(LibrarianController.class);

	@Autowired
	private LibrarianProfile librarian;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("the librarian");
		model.addAttribute("pageNo", 1);
		return "Librarian/viewLibraries";
	}

	@RequestMapping(value = "/AddBooksToLibrary", method = RequestMethod.POST)
	public String deletePublisher(@RequestParam("bookId") int bookId, @RequestParam("branchId") int branchId,
			@RequestParam("amount") int amount, Model model) throws SQLException {
		librarian.addBooksToLibrary(branchId, amount, bookId);
		logger.debug("deleting a nw autor");
		model.addAttribute("pageNo", 1);
		model.addAttribute("branchId", branchId);
		return "Librarian/addBookToLibrary";
	}

	@RequestMapping(value = "/AddBooksToLibrary", method = RequestMethod.GET)
	public String getAllBooksOfBranch(@RequestParam("branchId") int branchId, Model model) throws SQLException {
		model.addAttribute("pageNo", 1);
		model.addAttribute("branchId", branchId);
		logger.info("requesting all books for specific branch");
		return "Librarian/addBookToLibrary";
	}

	@RequestMapping(value = "/EditLibrary", method = RequestMethod.POST)
	public String addPostLibrary(@RequestParam("libId") int libId, @RequestParam("address") String libAddress,
			@RequestParam("name") String branchName, Model model) throws SQLException {
		LibraryBranch lib = new LibraryBranch();
		lib.setBranchName(branchName);
		lib.setBranchAddress(libAddress);
		lib.setBranchId(libId);

		librarian.editLibrary(lib);

		model.addAttribute("pageNo", 1);

		logger.info("adding autor");
		return "Librarian/viewLibraries";
	}
	
	@RequestMapping(value = "/EditLibrary", method = RequestMethod.GET)
	public String editGetLibrary(@RequestParam("branchId") int pubId, Model model) throws SQLException {
		logger.info("editting get library");
		return "Librarian/editLibrary";
	}


}
