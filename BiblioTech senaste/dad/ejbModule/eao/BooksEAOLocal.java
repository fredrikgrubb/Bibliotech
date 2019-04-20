package eao;

import java.util.List;

import javax.ejb.Local;

import entites.Book;
import entites.BookPK;

@Local
public interface BooksEAOLocal {

	public Book create(Book books);
	public Book update(Book books);
	public void remove(BookPK id);

	public Book getBookById(BookPK id);
	public List<Book> getAllBooks();
	//public void removeBook(Book book);
	List<Book> getBookByTitle(String title);
	List<Book> getBookByAuthor(String authorName);
}
