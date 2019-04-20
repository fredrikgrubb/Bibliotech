package facade;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import eao.BooksEAOLocal;
import eao.LoansEAOLocal;
import eao.UsersEAOLocal;
import entites.Book;
import entites.BookPK;
import entites.Loan;
import entites.LoanPK;
import entites.User;

/**
 * Session Bean implementation class Facade
 */
@Stateless
@LocalBean
public class Facade implements FacadeLocal {

	@EJB
	private UsersEAOLocal usersEAO;

	@EJB
	private BooksEAOLocal booksEAO;

	@EJB
	private LoansEAOLocal loansEAO;

	/**
	 * Default constructor.
	 */
	public Facade() {
		// TODO Auto-generated constructor stub
	}

	// User
	@Override
	public User create(User users) {
		return this.usersEAO.create(users);
	}

	@Override
	public User update(User users) {
		return this.usersEAO.update(users);
	}

	@Override
	public void removeUser(String userId) {
		this.usersEAO.remove(userId);
	}

	@Override
	public User getUserById(String userId) {
		return this.usersEAO.getUserById(userId);
	}

	@Override
	public List<User> getAllUsers() {
		return this.usersEAO.getAllUsers();
	}

	// Book
	@Override
	public Book create(Book books) {
		return this.booksEAO.create(books);
	}

	@Override
	public Book update(Book books) {
		return this.booksEAO.update(books);
	}

	@Override
	public void removeBook(BookPK id) {
		this.booksEAO.remove(id);
	}

	@Override
	public Book getBookById(BookPK id) {
		return this.booksEAO.getBookById(id);
	}

	@Override
	public List<Book> getAllBooks() {
		return this.booksEAO.getAllBooks();
	}

	@Override
	public List<Book> getBookByTitle(String title) {
		return this.booksEAO.getBookByTitle(title);
	}

	@Override
	public List<Book> getBookByAuthor(String authorName) {
		return this.booksEAO.getBookByAuthor(authorName);
	}

	// Loan
	@Override
	public Loan create(Loan loans) {
		return this.loansEAO.create(loans);
	}

	@Override
	public Loan update(Loan loans) {
		return this.loansEAO.update(loans);
	}

	@Override
	public void removeLoan(LoanPK id) {
		this.loansEAO.removeLoan(id);
	}

	@Override
	public Loan getLoanById(LoanPK id) {
		return this.loansEAO.getLoanById(id);
	}

	@Override
	public List<Loan> getAllLoans() {
		return this.loansEAO.getAllLoans();
	}

	@Override
	public List<Loan> getAllLoansForUser(User u) {
		return this.loansEAO.getAllLoansForUser(u);
	}

}
