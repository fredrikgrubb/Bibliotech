package entites;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the Books database table.
 * 
 */
@Embeddable
public class BookPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="ISBN")
	private String isbn;

	@Column(name="CopyNbr")
	private int copyNbr;

	
	
	public BookPK() {
	}
	public String getIsbn() {
		return this.isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public int getCopyNbr() {
		return this.copyNbr;
	}
	public void setCopyNbr(int copyNbr) {
		this.copyNbr = copyNbr;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof BookPK)) {
			return false;
		}
		BookPK castOther = (BookPK)other;
		return 
			this.isbn.equals(castOther.isbn)
			&& (this.copyNbr == castOther.copyNbr);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.isbn.hashCode();
		hash = hash * prime + this.copyNbr;
		
		return hash;
	}
}