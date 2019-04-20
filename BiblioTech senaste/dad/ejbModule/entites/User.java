package entites;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the Users database table.
 * 
 */
@Entity
@Table(name = "Users")
@NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "UserID")
	private String userID;

	@Column(name = "UserAdmin")
	private String userAdmin;

	@Column(name = "UserDebt")
	private int userDebt;

	@Column(name = "UserFirstName")
	private String userFirstName;

	@Column(name = "UserLastName")
	private String userLastName;

	@Column(name = "UserPassword")
	private String userPassword;

	// bi-directional many-to-one association to Loan
	@OneToMany(mappedBy = "user")
	private List<Loan> loans;

	public User() {
	}

	public String getUserID() {
		return this.userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getUserAdmin() {
		return this.userAdmin;
	}

	public void setUserAdmin(String userAdmin) {
		this.userAdmin = userAdmin;
	}

	public int getUserDebt() {
		return this.userDebt;
	}

	public void setUserDebt(int userDebt) {
		this.userDebt = userDebt;
	}

	public String getUserFirstName() {
		return this.userFirstName;
	}

	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}

	public String getUserLastName() {
		return this.userLastName;
	}

	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}

	public String getUserPassword() {
		return this.userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public List<Loan> getLoans() {
		return this.loans;
	}

	public void setLoans(List<Loan> loans) {
		this.loans = loans;
	}

	public Loan addLoan(Loan loan) {
		getLoans().add(loan);
		loan.setUser(this);

		return loan;
	}

	public Loan removeLoan(Loan loan) {
		getLoans().remove(loan);
		loan.setUser(null);

		return loan;
	}

}