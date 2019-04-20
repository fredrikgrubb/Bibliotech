package entites;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.*;

/**
 * The persistent class for the Loans database table.
 * 
 */
@Entity
@Table(name = "Loans")
@NamedQueries({ @NamedQuery(name = "Loan.findAll", query = "SELECT l FROM Loan l"),
		@NamedQuery(name = "Loan.findAllForUser", query = "SELECT l FROM Loan l WHERE l.user=:user"), })
public class Loan implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private LoanPK id;

	@Column(name = "ExpiryDate")
	private LocalDate expiryDate;

	@Column(name = "LoanDate")
	private LocalDate loanDate;

	@OneToOne
	@JoinColumns({
			@JoinColumn(name = "BookCopy", referencedColumnName = "CopyNbr", updatable = false, insertable = false),
			@JoinColumn(name = "BookISBN", referencedColumnName = "ISBN", updatable = false, insertable = false) })
	private Book book;

	@ManyToOne
	@JoinColumn(name = "UserID", insertable = false, updatable = false)
	private User user;

	public Loan() {
	}

	public LoanPK getId() {
		return this.id;
	}

	public void setId(LoanPK id) {
		this.id = id;
	}

	public LocalDate getExpiryDate() {
		return this.expiryDate;
	}

	public void setExpiryDate(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}

	public LocalDate getLoanDate() {
		return this.loanDate;
	}

	public void setLoanDate(LocalDate loanDate) {
		this.loanDate = loanDate;
	}

	public Book getBook() {
		return this.book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}