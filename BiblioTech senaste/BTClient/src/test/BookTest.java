package test;

import javax.naming.Context;
import javax.naming.InitialContext;

import entites.Author;
import entites.Book;
import entites.BookPK;
import facade.FacadeLocal;
import junit.framework.TestCase;

public class BookTest extends TestCase {
	FacadeLocal facade;

	public BookTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		Context context = new InitialContext();

		facade = (FacadeLocal)context.lookup("java:app/dad/Facade!facade.FacadeLocal");
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		facade = null;
	}

	public void testBookMethods() throws Exception {
		Book entitiesBook = new Book();
		BookPK bpk = new BookPK();
		Author a = new Author();
		entitiesBook.setTitle("Sagan om Ringen");
		a.setAuthorID(99);
		a.setAuthorName("J.R.R. Tolkien");
		entitiesBook.setAuthor(a);
		bpk.setCopyNbr(7);
		bpk.setIsbn("1234");
		entitiesBook.setId(bpk);
		assertEquals(entitiesBook.getTitle(), "Sagan om Ringen");
		assertEquals(entitiesBook.getAuthor(), a);
		assertEquals(entitiesBook.getId(), bpk);
	}

	public void testBookMethods2() throws Exception {
		Book entitiesBook = new Book();
		BookPK bpk = new BookPK();
		Author a = new Author();
		entitiesBook.setTitle("Lejonkungen");
		a.setAuthorID(15);
		a.setAuthorName("Disney");
		entitiesBook.setAuthor(a);
		bpk.setCopyNbr(66);
		bpk.setIsbn("9876");
		entitiesBook.setId(bpk);
		assertEquals(entitiesBook.getTitle(), "Lejonkungen");
		assertEquals(entitiesBook.getAuthor(), a);
		assertEquals(entitiesBook.getId(), bpk);

	}
}