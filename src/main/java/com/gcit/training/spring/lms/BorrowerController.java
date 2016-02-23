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

import com.gcit.training.spring.lms.service.BorrowerProfiles;
import com.gcit.training.spring.lms.service.entities.AuthorService;
import com.gcit.training.spring.lms.service.entities.BorrowerService;

@Controller
@RequestMapping(value = "/Borrower")
public class BorrowerController {

	private static final Logger logger = LoggerFactory.getLogger(BorrowerController.class);

	@Autowired
	private BorrowerProfiles borrowerProfile;

	// this only should send yes o not
	@RequestMapping(value = "/CheckValidCard", method = RequestMethod.POST)
	public String checkValidCard(@RequestParam("cardNo") int cardNo, Model model) {
		boolean b = borrowerProfile.checkValidCard(cardNo);
		if (b) {
			model.addAttribute("cardNo", cardNo);
			model.addAttribute("pageNo", 1);
			return "Borrower/viewLibraries";
		} else
			System.out.println("cardNo not valid");
		return "home";

	}

	@RequestMapping(value = "/CheckValidCard", method = RequestMethod.GET)
	public String checkGETValidCard(@RequestParam("cardNo") int cardNo, Model model) {
		model.addAttribute("cardNo", cardNo);
		model.addAttribute("pageNo", 1);
		return "Borrower/viewLibraries";

	}

	// TODO the user once hi enter to this page it cannot be unlogged
	@RequestMapping(value = "/ViewLibraries", method = RequestMethod.GET)
	public String viewLibraries(@RequestParam("pageNo") int pageNo, @RequestParam("cardNo") int cardNo, Model model) {
		model.addAttribute("branch", borrowerProfile.getAllBranches(pageNo));
		model.addAttribute("cardNo", cardNo);
		model.addAttribute("pageNo", pageNo);
		return "Borrower/viewLibraries";
	}

	// TODO the user once hi enter to this page it cannot be unlogged
	@RequestMapping(value = "/ViewBookBranch", method = RequestMethod.GET)
	public String viewLibrariesBooks(@RequestParam("branchId") int branchId, @RequestParam("cardNo") int cardNo,
			Model model) throws SQLException {
		model.addAttribute("cardNo", cardNo);
		model.addAttribute("branchId", branchId);
		return "Borrower/checkOut";
	}

	@RequestMapping(value = "/CheckOut", method = RequestMethod.POST)
	public String CheckOutBook(@RequestParam("bookId") int bookId, @RequestParam("branchId") int branchId,
			@RequestParam("cardNo") int cardNo, Model model) throws SQLException {
		model.addAttribute("cardNo", cardNo);
		model.addAttribute("branchId", branchId);

		borrowerProfile.checkOut(branchId, bookId, cardNo);

		return "Borrower/checkOut";
	}

	@RequestMapping(value = "/CheckIn", method = RequestMethod.POST)
	public String CheckInBook(@RequestParam("bookId") int bookId, @RequestParam("branchId") int branchId,
			@RequestParam("pageNo") int pageNo, @RequestParam("cardNo") int cardNo, Model model) throws SQLException {
		model.addAttribute("cardNo", cardNo);
		model.addAttribute("pageNo", pageNo);

		System.out.println("touching the checkIn method");
		borrowerProfile.checIn(bookId, branchId, cardNo);

		return "Borrower/checkIn";
	}

	@RequestMapping(value = "/CheckIn", method = RequestMethod.GET)
	public String GetCheckInBook(@RequestParam("pageNo") int pageNo, @RequestParam("cardNo") int cardNo, Model model)
			throws SQLException {
		model.addAttribute("cardNo", cardNo);
		model.addAttribute("pageNo", pageNo);
		return "Borrower/checkIn";
	}

}
