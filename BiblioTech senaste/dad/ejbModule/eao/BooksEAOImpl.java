package eao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entites.Book;
import entites.BookPK;

/**
 * Session Bean implementation class BooksEAOImpl
 */
@Stateless
@LocalBean
public class BooksEAOImpl implements BooksEAOLocal {

	@PersistenceContext(unitName = "dad")
	private EntityManager em;

	/**
	 * Default constructor.
	 */
	public BooksEAOImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Book create(Book books) {
		em.persist(books);
		return books;
	}

	@Override
	public Book update(Book books) {
		em.merge(books);
		return books;
	}

	/*
	 * @Override public void remove(String isbn) {
	 * 
	 * Book books = this.getBookById(isbn); if (books != null) { em.remove(books); }
	 * }
	 */
	@Override
	public void remove(BookPK id) {
		Book book = this.getBookById(id);
		if (book != null) {
			em.remove(book);
		}
	}

	@Override
	public List<Book> getAllBooks() {
		TypedQuery<Book> query = em.createNamedQuery("Book.findAll", Book.class);
		List<Book> results = query.getResultList();
		return results;
	}

	@Override
	public Book getBookById(BookPK id) {
		return em.find(Book.class, id);
	}

	@Override
	public List<Book> getBookByTitle(String title) {
		TypedQuery<Book> query = em.createNamedQuery("Book.findByTitle", Book.class);
		query.setParameter("title", "%" + title + "%");
		List<Book> results = query.getResultList();
		return results;
	}

	@Override
	public List<Book> getBookByAuthor(String authorName) {
		TypedQuery<Book> query = em.createNamedQuery("Book.findByAuthor", Book.class);
		query.setParameter("authorName", "%" + authorName + "%");
		List<Book> results = query.getResultList();
		return results;
	}

}
