package com.gcit.training.spring.lms.dao.bookLoans;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.gcit.training.spring.lms.dao.LibraryDAO;
import com.gcit.training.spring.lms.entity.Books_Loans;
import com.gcit.training.spring.lms.entity.Genre;

public class ExtractFirstDataBookLoans implements ResultSetExtractor<List<Books_Loans>> {

	@Override
	public List<Books_Loans> extractData(ResultSet rs) {
		List<Books_Loans> bookLoanss = new ArrayList<Books_Loans>();

		try {
			while (rs.next()) {
				Books_Loans bookLoans = new Books_Loans();
				bookLoans.setDateIn(rs.getTimestamp("dateIn"));
				bookLoans.setDateOut(rs.getTimestamp("dateOut"));
				bookLoans.setDueDate(rs.getTimestamp("dueDate"));
				bookLoanss.add(bookLoans);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bookLoanss;
	}

}
