package eao;

import java.util.List;

import javax.ejb.Local;

import entites.Loan;
import entites.LoanPK;
import entites.User;


@Local
public interface LoansEAOLocal {
	public Loan create(Loan loans);

	public Loan update(Loan loans);

	public void removeLoan(LoanPK id);

	public Loan getLoanById(LoanPK id);

	public List<Loan> getAllLoans();

	public List<Loan> getAllLoansForUser(User u);

}
