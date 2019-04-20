package entites;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the Loans database table.
 * 
 */
@Embeddable
public class LoanPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="BookISBN")
	private String bookISBN;

	@Column(name="BookCopy")
	private int bookCopy;

	@Column(name="UserID")
	private String userID;

	public LoanPK() {
	}
	public String getBookISBN() {
		return this.bookISBN;
	}
	public void setBookISBN(String bookISBN) {
		this.bookISBN = bookISBN;
	}
	public int getBookCopy() {
		return this.bookCopy;
	}
	public void setBookCopy(int bookCopy) {
		this.bookCopy = bookCopy;
	}
	public String getUserID() {
		return this.userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof LoanPK)) {
			return false;
		}
		LoanPK castOther = (LoanPK)other;
		return 
			this.bookISBN.equals(castOther.bookISBN)
			&& (this.bookCopy == castOther.bookCopy)
			&& this.userID.equals(castOther.userID);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.bookISBN.hashCode();
		hash = hash * prime + this.bookCopy;
		hash = hash * prime + this.userID.hashCode();
		
		return hash;
	}
}