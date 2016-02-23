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
import com.gcit.training.spring.lms.entity.Genre;
import com.gcit.training.spring.lms.service.AdminstratorProfile;
import com.gcit.training.spring.lms.service.entities.AuthorService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private AdminstratorProfile admin;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "home";
	}

	@RequestMapping(value = "/searchAuthors", method = RequestMethod.GET)
	@ResponseBody
	public String searchAuthor(Locale locale, Model model, @RequestParam(value = "searchString") String searchString)
			throws SQLException {
		List<Author> lst = admin.searchAuthorByName(searchString, -1);
		int count = lst.size();
		int pages = count / 10;
		if (count % 10 != 0)
			pages++;
		StringBuilder sb = new StringBuilder();
		sb.append("<nav><ul class='pagination'>");
		for (int i = 1; i <= pages; i++) {
			sb.append("<li><a id='page' onclick='paging(" + i + ");'>" + i + "</a></li>");
		}
		sb.append("</ul></nav>");
		logger.info(sb.toString());
		return sb.toString();
	}

	@RequestMapping(value = "/pageAuthors", method = RequestMethod.GET)
	@ResponseBody
	public String searchAuthors(@RequestParam(value = "searchString", required = false) String searchString,
			@RequestParam(value = "pageNo", required = false) Integer pageNo, Model model)
					throws ParseException, SQLException {
		if (pageNo == null)
			pageNo = 1;
		List<Author> authors = admin.searchAuthorByName(searchString, pageNo);
		logger.info("pageAuthors request");
		StringBuilder sb = new StringBuilder();
		sb.append("<table class='table table-striped table-bordered table-hover' id='authorsTable'>");
		sb.append("<tr><th>Author ID</th>");
		sb.append("<th>Author Name</th>");
		sb.append("<th>Book Title</th>");
		sb.append("<th>EDIT</th>");
		sb.append("<th>DELETE</th></tr>");
		for (Author a : authors) {
			sb.append("<tr><td>" + a.getAuthorId() + "</td><td>" + a.getAuthorName() + "</td><td>");

			for (Book b : a.getBooks()) {
				sb.append(b.getTitle() + ", ");
			}
			sb.append("</td><td><button type='button' "
					+ "class='btn btn btn-info' data-toggle='modal' data-target='#myModal1'"
					+ "href='Administrator/EditAuthor?authorId=" + a.getAuthorId() + "'>EDIT</button></td><td>"
					+ "<form method='post' action='Administrator/DeleteAuthor'>"
					+ "<input type='hidden' name='authorId' value=" + a.getAuthorId() + ">"
					+ "<button type='submit' class='btn btn btn-danger'>Delete</button></form>" + "</td></tr>");
			logger.info("the author" + a.getAuthorName());
		}
		logger.info(sb.toString());
		return sb.toString();
	}

	//////////////////// books

	@RequestMapping(value = "/searchBooks", method = RequestMethod.GET)
	@ResponseBody
	public String searchBooks(Locale locale, Model model,
			@RequestParam(value = "searchString", required = false) String searchString) throws SQLException {
		List<Book> lst = admin.searchBookByTitle(searchString, -1);
		int count = lst.size();
		int pages = count / 10;
		if (count % 10 != 0)
			pages++;
		StringBuilder sb = new StringBuilder();
		sb.append("<nav><ul class='pagination'>");
		for (int i = 1; i <= pages; i++) {
			sb.append("<li><a id='page' onclick='paging(" + i + ");'>" + i + "</a></li>");
		}
		sb.append("</ul></nav>");
		logger.info(sb.toString());
		return sb.toString();
	}

	@RequestMapping(value = "/pageBooks", method = RequestMethod.GET)
	@ResponseBody
	public String searchBooks(@RequestParam(value = "searchString", required = false) String searchString,
			@RequestParam(value = "pageNo", required = false) Integer pageNo, Model model)
					throws ParseException, SQLException {
		if (pageNo == null)
			pageNo = 1;

		List<Book> books = admin.searchBookByTitle(searchString, pageNo);
		logger.info("pageAuthors request");
		StringBuilder sb = new StringBuilder();
		sb.append("<table class='table table-striped table-bordered table-hover' id='booksTable'>");

		sb.append("<tr><th>Book Title</th>");
		sb.append("<th>Publisher</th>");
		sb.append("<th>Book Authors</th>");
		sb.append("<th>Book Genres</th>");
		sb.append("<th>Edit</th>");
		sb.append("<th>DELETE</th></tr>");
		for (Book a : books) {
			sb.append("<tr><td>" + a.getTitle() + "</td><td>" + a.getPublisher().getPublisherName() + "</td><td>");

			for (Author b : a.getAuthors()) {
				sb.append(b.getAuthorName() + ", ");
			}
			sb.append("</td><td>");

			for (Genre g : a.getGenre()) {
				sb.append(g.getGenreName() + ", ");

			}
			sb.append("</td><td><button type='button' "
					+ "class='btn btn btn-info' data-toggle='modal' data-target='#myModalbook'"
					+ "href='Administrator/EditBook?bookId=" + a.getBookId() + "'>EDIT</button></td><td>"
					+ "<form method='post' action='Administrator/DeleteBook'>"
					+ "<input type='hidden' name='bookId' value=" + a.getBookId() + ">"
					+ "<button type='submit' class='btn btn btn-danger'>Delete</button></form>" + "</td></tr>");
		}
		logger.info(sb.toString());
		return sb.toString();
	}

}
