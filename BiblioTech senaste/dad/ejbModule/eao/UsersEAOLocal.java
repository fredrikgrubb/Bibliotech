package eao;

import java.util.List;

import javax.ejb.Local;

import entites.User;

@Local
public interface UsersEAOLocal {

	public User create(User users);
	public User update(User users);
	public void remove(String userId);

	public User getUserById(String userId);
	public List<User> getAllUsers();
}
