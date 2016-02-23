package com.gcit.training.spring.lms.service.entities;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.gcit.training.spring.lms.dao.Book_GenreDAO;
import com.gcit.training.spring.lms.dao.genre.GenreDAO;
import com.gcit.training.spring.lms.entity.Genre;

public class GenreService {

	@Autowired
	GenreDAO genreDAO;

	@Autowired
	Book_GenreDAO bookGenreDAO;

	// ////////////////////////////////// AUTHORS///////////////////////////
	public int addGenre(Genre genre) {
		return genreDAO.add(genre);
	}

	public List<Genre> readGenre(int pageNo) {
		return genreDAO.readAll(pageNo);
	}

	public void editGenre(Genre genre) {
		genreDAO.update(genre);
	}

	public void deleteGenre(Genre genre) throws SQLException {
		genreDAO.delete(genre);
		bookGenreDAO.deleteBooksByGenre(genre.getGenreId());
	}

	public Genre getGenreById(int id) {
		return genreDAO.getById(id);
	}
	////////////////////////////////////////////////// Genre////////////////////////////

	public List<Genre> readFirstInfo(int i) {
		return genreDAO.readFirstLevel(i);
	}

}
