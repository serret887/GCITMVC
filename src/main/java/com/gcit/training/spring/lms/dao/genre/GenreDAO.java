package com.gcit.training.spring.lms.dao.genre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.gcit.training.spring.lms.dao.BaseDAO;
import com.gcit.training.spring.lms.dao.DAO;
import com.gcit.training.spring.lms.entity.Author;
import com.gcit.training.spring.lms.entity.Genre;

public class GenreDAO extends BaseDAO<Genre> implements DAO<Genre> {

	private static final String SELECT_BY_ID = "select * from tbl_genre where genre_id = ? ";
	private static final String UPDATE = "update tbl_genre set genre_name = ? where genre_id = ? ";
	private static final String DELETE = "delete from tbl_genre where genre_id = ? ";
	private static final String INSERT = "insert into tbl_genre (genre_name) values (?) ";
	private static final String SELECT = "select * from tbl_genre";
	private static final String SELECT_BY_NAME = "select * from tbl_genre where genre_name like ? ";

	@Autowired
	private ExtractFirstDataGenre extractFirstData;

	@Autowired
	private ExtractFullDataGenre extractFullData;

	@Override
	public List<Genre> readAll(int pageNo) {
		return template.query(SELECT, extractFullData);
	}

	@Override
	public List<Genre> readFirstLevel(int pageNo) {
		return template.query(SELECT, extractFirstData);
	}

	@Override
	public int add(Genre item) {
		final String genreName = item.getGenreName();

		KeyHolder keyHolder = new GeneratedKeyHolder();
		template.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(INSERT, new String[] { "id" });
				ps.setString(1, genreName);
				return ps;
			}
		}, keyHolder);
		return keyHolder.getKey().intValue();

	}

	@Override
	public Genre getById(int id) {
		List<Genre> genre = template.query(SELECT_BY_ID, new Object[] { id }, extractFirstData);
		if (genre.isEmpty())
			return null;
		return genre.get(0);
	}

	@Override
	public List<Genre> searchByName(String searchString, int pageNo) {
		return template.query(SELECT_BY_NAME, new Object[] { searchString }, extractFirstData);

	}

	@Override
	public void delete(Genre item) {
		template.update(DELETE, new Object[] { item.getGenreId() });
	}

	@Override
	public void update(Genre item) {
		template.update(UPDATE, new Object[] { item.getGenreName(), item.getGenreId() });
	}

	// @Override
	// public List<Genre> extractData(ResultSet rs) {
	// List<Genre> genres = new ArrayList<Genre>();
	// BookDAO adao = new BookDAO(conn);
	// try {
	// while(rs.next()){
	// Genre genre = new Genre();
	// genre.setGenreName(rs.getString("genre_name"));
	// genre.setGenreId(rs.getInt("genre_id"));
	// genre.setBooks(adao.readFirstLevel("select * from tbl_book where bookId
	// IN (select bookId from tbl_book_genres where genre_id = ?)",new
	// Object[]{genre.getGenreId()}));
	// genres.add(genre);
	// }
	// } catch (SQLException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// return genres;
	// }
	//
	// @Override
	// public List<Genre> extractDataFirstLevel(ResultSet rs) {
	// List<Genre> genres = new ArrayList<Genre>();
	// try {
	// while (rs.next()) {
	// Genre genre = new Genre();
	// genre.setGenreName(rs.getString("genre_name"));
	// genre.setGenreId(rs.getInt("genre_id"));
	// genres.add(genre);
	// }
	// } catch (SQLException e) {
	//
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// return genres;
	// }

}
