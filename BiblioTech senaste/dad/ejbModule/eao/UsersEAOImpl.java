package eao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entites.User;


/**
 * Session Bean implementation class UsersEAOImpl
 */
@Stateless
@LocalBean
public class UsersEAOImpl implements UsersEAOLocal {

	@PersistenceContext(unitName = "dad")
	private EntityManager em;

	/**
	 * Default constructor.
	 */
	public UsersEAOImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public User create(User users) {
		em.persist(users);
		return users;
	}

	@Override
	public User update(User users) {
		em.merge(users);
		return users;
	}

	@Override
	public void remove(String userId) {
		User users = this.getUserById(userId);
		if (users != null) {
			em.remove(users);
		}
	}

	@Override
	public List<User> getAllUsers() {
		TypedQuery<User> query = em.createNamedQuery("User.findAll", User.class);
		List<User> results = query.getResultList();
		return results;
	}

	@Override
	public User getUserById(String userId) {
		return em.find(User.class, userId);
	}

}
