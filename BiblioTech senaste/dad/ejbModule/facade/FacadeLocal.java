package facade;

import java.util.List;

import javax.ejb.Local;

import entites.Book;
import entites.BookPK;
import entites.Loan;
import entites.LoanPK;
import entites.User;

@Local
public interface FacadeLocal {

	//User
	public User create(User users);
	public User update(User users);
	public void removeUser(String userId);
	public User getUserById(String userId);
	public List<User> getAllUsers();
	
	
	// Book
	public Book create(Book books);
	public Book update(Book books);
	public void removeBook(BookPK id);

	public Book getBookById(BookPK id);
	public List<Book> getAllBooks();

	List<Book> getBookByAuthor(String authorName);
	List<Book> getBookByTitle(String title);

	//public void removeBook(Book book);
	
	// Loan
	public Loan create(Loan loans);

	public Loan update(Loan loans);

	public void removeLoan(LoanPK id);

	public Loan getLoanById(LoanPK id);

	public List<Loan> getAllLoans();

	public List<Loan> getAllLoansForUser(User u);
	
}


