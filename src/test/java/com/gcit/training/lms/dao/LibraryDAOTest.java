package com.gcit.training.lms.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.sql.SQLException;
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
import com.gcit.training.spring.lms.dao.LibraryDAO;
import com.gcit.training.spring.lms.entity.LibraryBranch;

@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { LMSConfig.class })
@ActiveProfiles("dev")
public class LibraryDAOTest {
	@Autowired
	LibraryDAO ldao;

	@Test
	@Transactional
	public void testAddLibrary() throws SQLException {
		LibraryBranch lib = new LibraryBranch();
		lib.setBranchAddress("test address");
		lib.setBranchName("test name");
		int libId = ldao.add(lib);
		List<LibraryBranch> libraries = ldao.readAll(1);
		assertEquals("mismatch id ", 4, libId);
		assertEquals("The amount of the libraries are not equals", 4, libraries.size());

	}

	@Test
	@Transactional
	public void testDeleteLibrary() {
		LibraryBranch lib = new LibraryBranch();
		lib.setBranchId(1);
		ldao.delete(lib);
		List<LibraryBranch> libraries = ldao.readAll(1);
		assertEquals("The amount of the libraries are not equals", 2, libraries.size());
	}

	@Test
	@Transactional
	public void testUpdateLibrary() {
		LibraryBranch lib = new LibraryBranch();
		lib.setBranchAddress("test address");
		lib.setBranchName("test name");
		lib.setBranchId(1);

		ldao.update(lib);
		LibraryBranch b = ldao.getById(1);
		assertEquals("the names does not match", "test name", b.getBranchName());
		assertEquals("the addres does not match", "test address", b.getBranchAddress());
	}

	@Test
	public void testReadAll() {
		List<LibraryBranch> libraries = ldao.readAll(1);
		assertEquals("The amount of the amount libraries are not equals", 3, libraries.size());
	}

	@Test
	public void testGetBranchById() {
		LibraryBranch b = ldao.getById(1);
		assertEquals("the name should be equals", "Weyerhauser Company", b.getBranchName());
		assertEquals("The addres should be equals", "94574 Heaney Pass", b.getBranchAddress());
		assertEquals("The id should be equals", 1, b.getBranchId());
	}

}
