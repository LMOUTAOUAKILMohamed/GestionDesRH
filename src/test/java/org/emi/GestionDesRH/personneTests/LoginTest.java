package org.emi.GestionDesRH.personneTests;
import static org.junit.Assert.*;

import org.junit.Test;

import ma.ac.emi.info.personne.Login;

public class LoginTest {
	
	Login login = new Login();
	
	@Test
	public void testIsValidLogin()
	{
		String identifier = "Admin";
		assertEquals(true, login.isValidLogin(identifier));
		assertEquals(false, login.isValidLogin("NonReconnu"));
	}
	
	@Test
	public void testIsValidLoginPassword()
	{
		String identifier = "Admin";
		String password = "123456";
		assertEquals(true, login.isValidLoginPassword(identifier, password));
		assertEquals(false, login.isValidLoginPassword("NonReconnu", "NonReconnu"));
		assertEquals(false, login.isValidLoginPassword(identifier, "NonReconnu"));
		assertEquals(false, login.isValidLoginPassword("NonReconnu", password));
	}
	
}
