package com.gcit.training.spring.lms.dao.bookLoans;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.gcit.training.spring.lms.dao.BorrowerDAO;
import com.gcit.training.spring.lms.dao.LibraryDAO;
import com.gcit.training.spring.lms.dao.book.BookDAO;
import com.gcit.training.spring.lms.entity.Books_Loans;

public class ExtractFullDataBookLoans implements ResultSetExtractor<List<Books_Loans>> {

	@Autowired
	BookDAO bdao;
	@Autowired
	LibraryDAO ldao;
	@Autowired
	BorrowerDAO borrowerDao;

	@Override
	public List<Books_Loans> extractData(ResultSet rs) {
		List<Books_Loans> bookLoanss = new ArrayList<Books_Loans>();

		try {
			while (rs.next()) {
				Books_Loans bookLoans = new Books_Loans();
				bookLoans.setBook(bdao.getById(rs.getInt("bookId")));
				bookLoans.setBorrower(borrowerDao.getById(rs.getInt("cardNo")));
				bookLoans.setBranch(ldao.getById(rs.getInt("branchId")));
				bookLoans.setDateIn(rs.getTimestamp("dateIn"));
				bookLoans.setDateOut(rs.getTimestamp("dateOut"));
				bookLoans.setDueDate(rs.getTimestamp("dueDate"));
				bookLoanss.add(bookLoans);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bookLoanss;
	}
}
