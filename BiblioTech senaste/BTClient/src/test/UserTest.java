package test;

import junit.framework.TestCase;
import javax.naming.Context;
import javax.naming.InitialContext;

import entites.User;
import facade.FacadeLocal;

public class UserTest extends TestCase {

	FacadeLocal facade;

	public UserTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		Context context = new InitialContext();
		facade = (FacadeLocal)context.lookup("java:app/dad/Facade!facade.FacadeLocal");			
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		facade = null;
	}
	public void testUsersMethods() throws Exception {
		User entitiesUser = new User();
		entitiesUser.setUserAdmin("Admin");
		entitiesUser.setUserDebt(100);
		entitiesUser.setUserFirstName("Mats");
		entitiesUser.setUserLastName("Erdogan");
		entitiesUser.setUserID("Anna");
		entitiesUser.setUserPassword("HataData");
		assertEquals(entitiesUser.getUserAdmin(), "Admin");
		assertEquals(entitiesUser.getUserDebt(), 100);
		assertEquals(entitiesUser.getUserFirstName(), "Mats");
		assertEquals(entitiesUser.getUserLastName(), "Erdogan");
		assertEquals(entitiesUser.getUserID(), "Anna");
		assertEquals(entitiesUser.getUserPassword(), "HataData");
	}
	public void testUsersMethods2() throws Exception {
		User entitiesUser = new User();
		entitiesUser.setUserAdmin("Press");
		entitiesUser.setUserDebt(999);
		entitiesUser.setUserFirstName("Kaj");
		entitiesUser.setUserLastName("Borje");
		entitiesUser.setUserID("AnyKey");
		entitiesUser.setUserPassword("Homer");
		assertEquals(entitiesUser.getUserAdmin(), "Press");
		assertEquals(entitiesUser.getUserDebt(), 999);
		assertEquals(entitiesUser.getUserFirstName(), "Kaj");
		assertEquals(entitiesUser.getUserLastName(), "Borje");
		assertEquals(entitiesUser.getUserID(), "AnyKey");
		assertEquals(entitiesUser.getUserPassword(), "Homer");
	}

}