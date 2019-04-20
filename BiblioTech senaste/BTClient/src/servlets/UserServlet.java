package servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entites.Book;
import entites.BookPK;
import entites.Loan;
import entites.LoanPK;
import entites.User;
import facade.FacadeLocal;

@WebServlet("/books")
public class UserServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@EJB
	private FacadeLocal facade;

	public UserServlet() {
		super();
	}

	// --------------------------------------------------------- doGet
	// --------------------------------
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");
		HttpSession session = request.getSession();

		if (action == null) {
			response.sendRedirect("index.jsp");

		} else {
			// används ej
			if (action.equalsIgnoreCase("add")) {
				request.setAttribute("listBooks", facade.getAllBooks());
				request.getRequestDispatcher("add.jsp").forward(request, response);
			} else if (action.equalsIgnoreCase("add2")) {
				LocalDate today = LocalDate.now();
				LocalDate twoWeeksFromToday = today.plus(2, ChronoUnit.WEEKS);

				Loan loan = new Loan();
				LoanPK id = new LoanPK();
				id.setBookISBN(request.getParameter("isbn"));
				String userID = request.getParameter("userID");
				id.setBookCopy(Integer.parseInt(request.getParameter("copyNbr")));
				id.setUserID(request.getParameter("userID"));
				loan.setId(id);
				loan.setLoanDate(today);
				loan.setExpiryDate(twoWeeksFromToday);
				facade.create(loan);

				User u = facade.getUserById(userID);

				List<Book> availBooks = facade.getAllBooks();
				List<Book> toRemove = new ArrayList<Book>();
				List<Loan> allLoans = facade.getAllLoans();
				for (Book b : availBooks) {
					for (Loan l : allLoans) {
						if (b.getId().getIsbn().equals(l.getId().getBookISBN()) && String
								.valueOf(b.getId().getCopyNbr()).equals(String.valueOf(l.getId().getBookCopy()))) {
							toRemove.add(b);
						}
					}
				}
				availBooks.removeAll(toRemove);
				request.setAttribute("listAvailableBooks", availBooks);
				request.setAttribute("listLoans", facade.getAllLoansForUser(u));
				request.getRequestDispatcher("index.jsp").forward(request, response);
				//request.getRequestDispatcher("add2.jsp").forward(request, response);
			} else if (action.equalsIgnoreCase("about")) {

				List<Book> availBooks = facade.getAllBooks();
				List<Book> toRemove = new ArrayList<Book>();
				List<Loan> allLoans = facade.getAllLoans();
				for (Book b : availBooks) {
					for (Loan l : allLoans) {
						if (b.getId().getIsbn().equals(l.getId().getBookISBN()) && String
								.valueOf(b.getId().getCopyNbr()).equals(String.valueOf(l.getId().getBookCopy()))) {
							toRemove.add(b);
						}
					}
				}
				availBooks.removeAll(toRemove);
				// get all available books end
				request.setAttribute("listBooks", availBooks);
				// request.setAttribute("listBooks", facade.getAllBooks());
				request.getRequestDispatcher("aboutPage.jsp").forward(request, response);
			} else if (action.equalsIgnoreCase("login")) {
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		}
		if (action.equalsIgnoreCase("returnBook")) {
			try {
				LoanPK id = new LoanPK();
				id.setBookISBN(request.getParameter("bookISBN"));
				String userID = request.getParameter("userID");
				id.setBookCopy(Integer.parseInt(request.getParameter("bookCopy")));
				id.setUserID(request.getParameter("userID"));
				facade.removeLoan(id);

				User u = facade.getUserById(userID);

				List<Book> availBooks = facade.getAllBooks();
				List<Book> toRemove = new ArrayList<Book>();
				List<Loan> allLoans = facade.getAllLoans();
				for (Book b : availBooks) {
					for (Loan l : allLoans) {
						if (b.getId().getIsbn().equals(l.getId().getBookISBN()) && String
								.valueOf(b.getId().getCopyNbr()).equals(String.valueOf(l.getId().getBookCopy()))) {
							toRemove.add(b);
						}
					}
				}
				availBooks.removeAll(toRemove);
				request.setAttribute("listAvailableBooks", availBooks);
				request.setAttribute("listLoans", facade.getAllLoansForUser(u));
				request.getRequestDispatcher("index.jsp").forward(request, response);
			} catch (Exception e) {
				request.setAttribute("error", e.getMessage());
			}
		}
		// ----------------------------------------------------------------------------------------LOGOUT
		if (action.equalsIgnoreCase("logout")) {
			try {
				session.invalidate();
				response.sendRedirect("index.jsp");
			}

			catch (Exception e) {
				request.setAttribute("error", e.getMessage());
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		}
	}
	// ----------------------------------------------------------------------------------------LOGOUT
	// END

	// --------------------------------------------------------- doPost
	// --------------------------------

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");
		HttpSession session = request.getSession();

		if (action == null) {
			response.sendRedirect("index.jsp");

		}
		// ----------------------------------------------------------------------------------------LOGIN
		if (action.equalsIgnoreCase("login")) {
			try {
				String userID = request.getParameter("userID");
				String userPassword = request.getParameter("userPassword");
				User user = facade.getUserById(userID);
				if (user != null && user.getUserPassword().equals(userPassword)) {
					// HttpSession session = request.getSession();
					session.setAttribute("User", userID);
					// request.setAttribute("listBooks", facade.getAllBooks());
					User u = facade.getUserById(userID);
					request.setAttribute("listLoans", facade.getAllLoansForUser(u));

					// get all available books
					List<Book> availBooks = facade.getAllBooks();
					List<Book> toRemove = new ArrayList<Book>();
					List<Loan> allLoans = facade.getAllLoans();
					for (Book b : availBooks) {
						for (Loan l : allLoans) {
							if (b.getId().getIsbn().equals(l.getId().getBookISBN()) && String
									.valueOf(b.getId().getCopyNbr()).equals(String.valueOf(l.getId().getBookCopy()))) {
								toRemove.add(b);
							}
						}
					}
					availBooks.removeAll(toRemove);
					// get all available books end
					request.setAttribute("listAvailableBooks", availBooks);

					request.getRequestDispatcher("index.jsp").forward(request, response);
				} else {
					response.sendRedirect("login.jsp");
					request.setAttribute("error", "Fel användarnamn eller lösenord!");
				}
			} catch (Exception e) {
				request.setAttribute("error", e.getMessage());
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		}
		// ----------------------------------------------------------------------------------------LOGIN
		// END

		// -------------------ANVÄNDS
		// EJ---------------------------------------------------------------------ADD
		if (action.equalsIgnoreCase("about")) {
			try {
				String title = request.getParameter("title");
				String authorName = request.getParameter("authorName");

				List<Book> availBooks = facade.getBookByTitle(title);
				List<Book> toRemove = new ArrayList<Book>();
				List<Loan> allLoans = facade.getAllLoans();
				for (Book b : availBooks) {
					for (Loan l : allLoans) {
						if (b.getId().getIsbn().equals(l.getId().getBookISBN()) && String
								.valueOf(b.getId().getCopyNbr()).equals(String.valueOf(l.getId().getBookCopy()))) {
							toRemove.add(b);
						}
					}
				}
				availBooks.removeAll(toRemove);
				request.setAttribute("booksByTitle", availBooks);

				List<Book> availBooks2 = facade.getBookByAuthor(authorName);
				List<Book> toRemove2 = new ArrayList<Book>();
				List<Loan> allLoans2 = facade.getAllLoans();
				for (Book b : availBooks2) {
					for (Loan l : allLoans2) {
						if (b.getId().getIsbn().equals(l.getId().getBookISBN()) && String
								.valueOf(b.getId().getCopyNbr()).equals(String.valueOf(l.getId().getBookCopy()))) {
							toRemove2.add(b);
						}
					}
				}
				availBooks2.removeAll(toRemove2);
				request.setAttribute("booksByAuthor", availBooks2);

				request.getRequestDispatcher("aboutPage.jsp").forward(request, response);
			} catch (Exception e) {
				request.setAttribute("error", e.getMessage());
				request.getRequestDispatcher("about.jsp").forward(request, response);
			}
		}
		// ----------------------------------------------------------------------------------------ADD
		// END

		// ----------------------------------------------------------------------------------------ADD2
		if (action.equalsIgnoreCase("add2")) {
			try {
				Loan loan = new Loan();
				LoanPK id = new LoanPK();
				String isbn = request.getParameter("bookISBN");
				String userID = request.getParameter("userID");
				int bookCopy = Integer.parseInt(request.getParameter("bookCopy"));
				BookPK bookID = new BookPK();
				bookID.setIsbn(isbn);
				bookID.setCopyNbr(bookCopy);

				Book book = new Book();
				book.setId(bookID);
				Book bookExist = facade.getBookById(bookID);

				id.setBookISBN(request.getParameter("bookISBN"));
				id.setBookCopy(Integer.parseInt(request.getParameter("bookCopy")));
				id.setUserID(request.getParameter("userID"));
				loan.setLoanDate(LocalDate.parse(request.getParameter("loanDate")));
				loan.setExpiryDate(LocalDate.parse((request.getParameter("expiryDate"))));
				loan.setId(id);
				if (bookExist != null) {

					facade.create(loan);
				} else {
					request.setAttribute("error", "Boken finns ej");
					request.getRequestDispatcher("add2.jsp").forward(request, response);

				}

				// get all available books
				List<Book> availBooks = facade.getAllBooks();
				List<Book> toRemove = new ArrayList<Book>();
				List<Loan> allLoans = facade.getAllLoans();
				for (Book b : availBooks) {
					for (Loan l : allLoans) {
						if (b.getId().getIsbn().equals(l.getId().getBookISBN()) && String
								.valueOf(b.getId().getCopyNbr()).equals(String.valueOf(l.getId().getBookCopy()))) {
							toRemove.add(b);
						}
					}
				}
				availBooks.removeAll(toRemove);
				// get all available books end
				request.setAttribute("listAvailableBooks", availBooks);
				User u = facade.getUserById(userID);
				// request.setAttribute("listBooks", facade.getAllBooks());
				request.setAttribute("listLoans", facade.getAllLoansForUser(u));
				request.getRequestDispatcher("index.jsp").forward(request, response);

			} catch (Exception e) {
				request.setAttribute("error", e.getMessage());
				request.getRequestDispatcher("add2.jsp").forward(request, response);
			}
		}
		// ----------------------------------------------------------------------------------------ADD2
		// END

		// ---------------------------ANVÄNDS
		// EJ-------------------------------------------------------------ADD3
		if (action.equalsIgnoreCase("add3")) {
			try {
				Loan loan = new Loan();
				LoanPK id = new LoanPK();
				id.setBookISBN(request.getParameter("bookISBN"));
				id.setBookCopy(Integer.parseInt(request.getParameter("bookCopy")));
				id.setUserID(request.getParameter("userID"));
				loan.setId(id);
				facade.create(loan);
				response.sendRedirect("books");
			} catch (Exception e) {
				request.setAttribute("error", e.getMessage());
				request.getRequestDispatcher("add2.jsp").forward(request, response);
			}
		}
		// ----------------------------------------------------------------------------------------ADD3
		// END
	}
}