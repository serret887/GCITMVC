package com.gcit.training.spring.lms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.gcit.training.spring.lms.entity.Borrower;

public class BorrowerDAO extends BaseDAO<Borrower> implements DAO<Borrower>, ResultSetExtractor<List<Borrower>> {

	private static final String DELETE = "delete from tbl_borrower where cardNo = ?";

	private static final String UPDATE = "update tbl_borrower set name = ? , address = ? , phone = ? where  cardNo = ?";

	private static final String INSERT = "insert into tbl_borrower (name, address, phone) values(?, ?, ?)";

	private static final String SELECT = "select * from tbl_borrower";

	private static final String SELECT_BY_NAME = "select * from tbl_borrower where name like ?";

	@Override
	public List<Borrower> extractData(ResultSet rs) throws SQLException {
		List<Borrower> borrowers = new ArrayList<Borrower>();
		while (rs.next()) {
			Borrower borrower = new Borrower();
			borrower.setName(rs.getString("name"));
			borrower.setCarNo(rs.getInt("cardNo"));
			borrower.setAddress(rs.getString("address"));
			borrower.setPhone(rs.getInt("phone"));
			borrowers.add(borrower);
		}
		return borrowers;
	}

	@Override
	public List<Borrower> readAll(int pageNo) {
		return template.query(SELECT, this);
	}

	@Override
	public List<Borrower> readFirstLevel(int pageNo) {
		return readAll(pageNo);
	}

	@Override
	public int add(Borrower item) {
		template.update(INSERT, new Object[] { item.getName(), item.getAddress(), item.getPhone() });
		return 0;
	}

	@Override
	public Borrower getById(int id) {

		List<Borrower> b = template.query("select * from tbl_borrower where cardNo = ?", new Object[] { id }, this);
		if (b.isEmpty())
			return null;
		return b.get(0);
	}

	@Override
	public List<Borrower> searchByName(String searchString, int pageNo) {
		return template.query(SELECT_BY_NAME, new Object[] { searchString }, this);
	}

	@Override
	public void delete(Borrower item) {
		template.update(DELETE, new Object[] { item.getCarNo() });
	}

	@Override
	public void update(Borrower item) {
		template.update(UPDATE, new Object[] { item.getName(), item.getAddress(), item.getPhone(), item.getCarNo() });
	}

}
