package entites;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "Books")
@NamedQueries({ @NamedQuery(name = "Book.findAll", query = "SELECT b FROM Book b"),
		@NamedQuery(name = "Book.findByTitle", query = "SELECT b FROM Book b WHERE b.title LIKE :title"),
		@NamedQuery(name = "Book.findByAuthor", query = "SELECT b FROM Book b JOIN b.author a WHERE a.authorName LIKE :authorName")})
public class Book implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private BookPK id;

	@Column(name = "Title")
	private String title;

	@ManyToOne
	@JoinColumn(name = "AuthorID")
	private Author author;

	@OneToOne(mappedBy = "book")
	private Loan loan;

	public Book() {
	}

	public BookPK getId() {
		return this.id;
	}

	public void setId(BookPK id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Author getAuthor() {
		return this.author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public Loan getLoan() {
		return this.loan;
	}

	public void setLoan(Loan loan) {
		this.loan = loan;
	}

}