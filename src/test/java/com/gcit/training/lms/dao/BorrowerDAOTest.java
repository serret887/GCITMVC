package com.gcit.training.lms.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.gcit.training.spring.LMSConfig;
import com.gcit.training.spring.lms.dao.BorrowerDAO;
import com.gcit.training.spring.lms.entity.Borrower;


@TransactionConfiguration(transactionManager="transactionManager" ,defaultRollback =true)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { LMSConfig.class })
@ActiveProfiles("dev")
@Transactional
public class BorrowerDAOTest {

	@Autowired
	BorrowerDAO bdao;

	@Test
	public void testReadAll() {
		List<Borrower> borrowers = bdao.readAll(1);
		assertEquals("the amount is diferent ", 2, borrowers.size());
	}

	@Test
	public void testReadFirstLevel() {
		List<Borrower> borrowers = bdao.readFirstLevel(1);
		assertEquals("the amount is diferent ", 2, borrowers.size());
	}

	@Test
	@Transactional
	public void testAdd() {
		Borrower item = new Borrower();
		item.setName("alex");
		bdao.add(item);

		Borrower actual = bdao.getById(3);
		assertEquals("the name of the borrower is incorrectT", "alex", actual.getName());

	}

	@Test
	public void testGetById() {
		Borrower actual = bdao.getById(2);
		assertEquals("the name of the borrower is incorrectT", "Shania Kutch", actual.getName());
		assertEquals("the addres of the borrower is incorrectT", "622 Margarette Turnpike Suite 391",
				actual.getAddress());
		assertEquals("the phone of the borrower is incorrectT", 35327, actual.getPhone());
	}

	@Test
	public void testSearchByName() {
		List<Borrower> actual = bdao.searchByName("Shania Kutch", 1);

		assertEquals("the amount is diferent ", 1, actual.size());

	}

	@Test
	@Transactional
	public void testDelete() {
		Borrower actual = bdao.getById(2);
		bdao.delete(actual);
		assertNull("teh borrower still exist", bdao.getById(2));
	}

	@Test
	@Transactional
	public void testUpdate() {
		Borrower actual1 = bdao.getById(2);
		actual1.setName("alex");
		bdao.update(actual1);
		Borrower actual = bdao.getById(2);
		assertEquals("the name of the borrower is incorrectT", "alex", actual.getName());

	}

}
