package com.gcit.training.lms.Integration;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.gcit.training.spring.LMSConfig;

@TransactionConfiguration(transactionManager="transactionManager" ,defaultRollback =true)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {LMSConfig.class})
@ActiveProfiles("dev")
public class AdministratorProfileTest {

	
	
	
	
	
	
	@Test
	public void testAddAuthor() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAuthorsFullData() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAuthorsSimpleData() {
		fail("Not yet implemented");
	}

	@Test
	public void testEditAuthor() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteAuthor() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetBooksFullData() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddBook() {
		fail("Not yet implemented");
	}

	@Test
	public void testEditBook() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteBook() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddPulisher() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPublishersFullData() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPublishers() {
		fail("Not yet implemented");
	}

	@Test
	public void testEditPublisher() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeletePulbisher() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddBorrower() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetBorrowersFullData() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetBorrowers() {
		fail("Not yet implemented");
	}

	@Test
	public void testEditBorrower() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteBorrower() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetGenresFullData() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetGenres() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddGenre() {
		fail("Not yet implemented");
	}

	@Test
	public void testEditGenre() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteGenre() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddLibrary() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetLibraries() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetLibrariesFullData() {
		fail("Not yet implemented");
	}

	@Test
	public void testEditLibrary() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteLibrary() {
		fail("Not yet implemented");
	}

}
