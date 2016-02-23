package com.gcit.training.spring.lms.dao.author;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.gcit.training.spring.lms.dao.BaseDAO;
import com.gcit.training.spring.lms.dao.DAO;
import com.gcit.training.spring.lms.entity.Author;

public class AuthorDAO extends BaseDAO<Author> implements DAO<Author> {

	private static final String INSERT_AUTHOR = "insert into tbl_author (authorName) values(?)";
	// TODO FIX THIS DUMMY DEPENDENCY IS WRONG

	private final String UPDATE_TBL_BY_ID = "update tbl_author set authorName = ? where authorId = ?";
	private final String DELETE_BY_ID = " delete from tbl_author where authorId  = ? ";
	private final String SELECT_BY_ID = " select * from tbl_author where authorId = ? ";
	private final String SELECT_ALL = " select * from tbl_author ";

	@Autowired
	private ExtractFirstDataAuthor extractFirstData;

	@Autowired
	private ExtractFullDataAuthor extractFullData;

	@Override
	public List<Author> readAll(int pageNo) {
		if (pageNo != -1) {
			setPageNo(pageNo);
			int[] minMax = limits();
		return 	template.query("select * from tbl_author limit ?,?", new Object[]{minMax[0],getPageSize()},extractFullData);
		}
		return template.query(SELECT_ALL, extractFullData);
	}

	@Override
	public Author getById(int id) {
		List<Author> author = template.query(SELECT_BY_ID, new Object[] { id }, extractFullData);
		if (author.isEmpty())
			return null;
		return author.get(0);
	}

	@Override
	public List<Author> searchByName(String searchString, int pageNo) {
		if(searchString == ""){
			return readAll(pageNo);
		}
		if (pageNo != -1) {
			setPageNo(pageNo);
			int[] minMax = limits();
			final String SELECT_BY_NAME = " select * from tbl_author where authorName like '%" + searchString
					+ "%' limit ? , ?";

			List<Author> a = template.query(SELECT_BY_NAME, new Object[] { minMax[0], getPageSize() }, extractFullData);
			return a;
		} else {
			return template.query("select * from tbl_author where authorName like '%" + searchString + "%'",
					extractFullData);
		}
	}

	@Override
	public int add(Author item) {
		final String authorName = item.getAuthorName();
		KeyHolder keyHolder = new GeneratedKeyHolder();
		template.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(INSERT_AUTHOR, new String[] { "id" });
				ps.setString(1, authorName);
				return ps;
			}
		}, keyHolder);
		return keyHolder.getKey().intValue();

	}

	@Override
	public void delete(Author item) {
		template.update(DELETE_BY_ID, new Object[] { item.getAuthorId() });
	}

	@Override
	public void update(Author item) {
		template.update(UPDATE_TBL_BY_ID, new Object[] { item.getAuthorName(), item.getAuthorId() });

	}

	@Override
	public List<Author> readFirstLevel(int pageNo) {
		return template.query(SELECT_ALL, extractFirstData);
	}

}
