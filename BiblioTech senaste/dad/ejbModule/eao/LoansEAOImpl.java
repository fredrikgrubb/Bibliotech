package eao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entites.Loan;
import entites.LoanPK;
import entites.User;

/**
 * Session Bean implementation class LoansEAOImpl
 */
@Stateless
@LocalBean
public class LoansEAOImpl implements LoansEAOLocal {

	
	@PersistenceContext(unitName = "dad")
	private EntityManager em;

    /**
     * Default constructor. 
     */
    public LoansEAOImpl() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public Loan create(Loan loans) {
		em.persist(loans);
		return loans;
	}

	@Override
	public Loan update(Loan loans) {
		em.merge(loans);
		return loans;
	}

	@Override
	public void removeLoan(LoanPK id) {
		Loan loans = this.getLoanById(id);
		if (loans != null) {
			em.remove(loans);
		}
	}

	@Override
	public List<Loan> getAllLoans() {
		TypedQuery<Loan> query = em.createNamedQuery("Loan.findAll", Loan.class);
		List<Loan> results = query.getResultList();
		return results;
	}

	@Override
	public Loan getLoanById(LoanPK id) {
		return em.find(Loan.class, id);
	}
	
	@Override
	public List<Loan> getAllLoansForUser(User u) {
		TypedQuery<Loan> query = em.createNamedQuery("Loan.findAllForUser", Loan.class);
		query.setParameter("user", u);
		List<Loan> results = query.getResultList();
		return results;
	}

}
