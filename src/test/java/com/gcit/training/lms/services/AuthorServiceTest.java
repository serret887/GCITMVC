package com.gcit.training.lms.services;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gcit.training.spring.LMSConfig;
import com.gcit.training.spring.lms.dao.Book_AuthorsDAO;
import com.gcit.training.spring.lms.dao.author.AuthorDAO;
import com.gcit.training.spring.lms.entity.Author;
import com.gcit.training.spring.lms.service.entities.AuthorService;

//@TransactionConfiguration(transactionManager="transactionManager" ,defaultRollback =true)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { LMSConfig.class })
@ActiveProfiles("dev")
// @Transactional
public class AuthorServiceTest {

	@InjectMocks
	private AuthorService authorService;

	@Mock
	private AuthorDAO authordao;
	@Mock
	private Book_AuthorsDAO bookAuthorDAO;

	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void test_read_authors_invoke_read_from_authorDAO() {
		authorService.readAuthors(1);
		verify(authordao, times(1)).readAll(1);
	}

	@Test
	public void test_read_authors_first_info(){
		authorService.readFirstInfo(1);
		verify(authordao, times(1)).readFirstLevel(1);
	}
	
	@Test
	public void test_delete_author_delete_dependencies_too() {
		Author author = new Author();
		author.setAuthorId(1);
		authorService.deleteAuthor(author);
		verify(authordao, times(1)).delete(author);
		verify(bookAuthorDAO, times(1)).deleteBooksOfAuthor(1);
	}

	@Test
	public void test_when_editing_an_author_his_books_areChanged() {
		Author author = new Author();
		author.setAuthorName("test");
		author.setAuthorId(2);
		int[] bookIds = { 1, 2, 3 };
		authorService.editAuthor(author, bookIds);
		verify(authordao, times(1)).update(author);
		verify(bookAuthorDAO, times(1)).updateBooksofAuthor(author, bookIds);
	}

	@Test
	public void test_when_adding_author_the_method_is_call() {
		authorService.addAuthor(new Author());
		verify(authordao, times(1)).add(any());
	}

	@Test
	public void test_when_get_author_by_the_method_is_call_in_AuthorDAO() {
		authorService.getAuthorById(1);
		verify(authordao, times(1)).getById(1);
	}

}
