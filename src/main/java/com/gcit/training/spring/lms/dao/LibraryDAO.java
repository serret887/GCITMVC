package com.gcit.training.spring.lms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.gcit.training.spring.lms.entity.Author;
import com.gcit.training.spring.lms.entity.LibraryBranch;

public class LibraryDAO extends BaseDAO<LibraryBranch>
		implements DAO<LibraryBranch>, ResultSetExtractor<List<LibraryBranch>> {

	private static final String SELECT_ALL = "select * from tbl_library_branch";
	private static final String DELETE_BY_ID = "delete from tbl_library_branch where branchId = ?";
	private static final String UPDATE = "update tbl_library_branch set branchName = ? , branchAddress = ? where branchId = ?";
	private static final String SELECT_BY_ID = "select * from tbl_library_branch where tbl_library_branch.branchId = ?";
	private final String INSERT = "insert into tbl_library_branch (branchName , branchAddress) values(?,?)";
	private final String SELECT_BY_NAME = " select * from tbl_library_branch where branchName like ? ";
//
//	public void deleteLibrary(LibraryBranch library) throws SQLException {
//		// // delete reference to table loans
//		// save("delete from tbl_book_loans where branchId = ?", new Object[] {
//		// library.getBranchId() });
//		// // delete reference to table books copies
//		// save("delete from tbl_book_copies where branchId = ?", new Object[] {
//		// library.getBranchId() });
//		// save("delete from tbl_library_branch where branchId = ?", new
//		// Object[] { library.getBranchId() });
//	}
//
////	public void updateLibrary(LibraryBranch library) throws SQLException {
////		// save("update tbl_library_branch set branchName = ? , branchAddress =
////		// ? where branchId = ?",
////		// new Object[] { library.getBranchName(), library.getBranchAddress(),
////		// library.getBranchId() });
////	}

	@Override
	public List<LibraryBranch> readAll(int id) {
		return template.query(SELECT_ALL, this);
	}

	@Override
	public List<LibraryBranch> extractData(ResultSet rs) throws SQLException {
		List<LibraryBranch> librarys = new ArrayList<LibraryBranch>();
		while (rs.next()) {
			LibraryBranch library = new LibraryBranch();
			library.setBranchName(rs.getString("branchName"));
			library.setBranchId(rs.getInt("branchId"));
			library.setBranchAddress(rs.getString("branchAddress"));
			librarys.add(library);
		}
		return librarys;
	}

	@Override
	public List<LibraryBranch> readFirstLevel(int pageNo) {
		return readAll(pageNo);
	}

	@Override
	public int add(LibraryBranch item) {
		final String libraryName = item.getBranchName();
		final String libraryAddress = item.getBranchAddress();
		KeyHolder keyHolder = new GeneratedKeyHolder();
		template.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(INSERT, new String[] { "id" });
				ps.setString(1, libraryName);
				ps.setString(2, libraryAddress);
				return ps;
			}
		}, keyHolder);
		return keyHolder.getKey().intValue();
	}

	@Override
	public LibraryBranch getById(int id) {
		List<LibraryBranch> b = template.query(SELECT_BY_ID, new Object[] { id }, this);
		if (b.isEmpty())
			return null;
		return b.get(0);
	}

	@Override
	public List<LibraryBranch> searchByName(String searchString, int pageNo) {
		return template.query(SELECT_BY_NAME, new Object[] { searchString }, this);
	}

	@Override
	public void delete(LibraryBranch item) {
		template.update(DELETE_BY_ID, new Object[] { item.getBranchId() });

	}

	@Override
	public void update(LibraryBranch item) {
		template.update(UPDATE, new Object[] { item.getBranchName(), item.getBranchAddress(), item.getBranchId() });

	}
}
