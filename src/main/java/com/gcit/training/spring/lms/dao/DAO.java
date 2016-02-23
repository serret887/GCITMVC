package com.gcit.training.spring.lms.dao;

import java.util.List;

public interface DAO<T> {

	public List<T> readAll(int pageNo);

	public List<T> readFirstLevel(int pageNo);

	public int add(T item);

	public T getById(int id);

	public List<T> searchByName(String searchString, int pageNo);

	public void delete(T item);

	public void update(T item);
}
