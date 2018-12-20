package ma.ac.emi.info.personne;

public class Login {
	
	public Login() {
		
	}

	public boolean isValidLogin(String login) {
		
		return login == "Admin" ? true : false;
		
	}

	public boolean isValidLoginPassword(String login, String password) {
		
		return login == "Admin" && password == "123456" ? true : false;
		
	}

}
