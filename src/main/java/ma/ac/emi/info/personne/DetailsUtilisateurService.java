package ma.ac.emi.info.personne;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ma.ac.emi.info.enseignantPermanent.EnseignantPermanentRepository;
import ma.ac.emi.info.enseignantVacataire.EnseignantVacataireRepository;
import ma.ac.emi.info.etudiant.EtudiantRepository;

@Service
public class DetailsUtilisateurService implements UserDetailsService{
	
	@Autowired
	EtudiantRepository etudiantRepository;
	
	@Autowired
	EnseignantPermanentRepository enseignantPermanentRepository;
	
	@Autowired
	EnseignantVacataireRepository enseignantVacataireRepository;
	
	public DetailsUtilisateurService(EnseignantPermanentRepository enseignantPermanentRepository) {
		this.enseignantPermanentRepository = enseignantPermanentRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String emailprofessionnel) throws UsernameNotFoundException{
		
		UserBuilder builder = null;
		
		Personne user = enseignantPermanentRepository.findByEmailProfessionnel(emailprofessionnel);
		if(user == null)
			user = enseignantVacataireRepository.findByEmailProfessionnel(emailprofessionnel);
		if(user == null)
			user = etudiantRepository.findByEmailProfessionnel(emailprofessionnel);
		if(user == null)
			throw new UsernameNotFoundException("Aucun utilisateur existe avec l'email " + emailprofessionnel );
		
		builder = User.withUsername(emailprofessionnel);
		builder.password(user.getMotDePasse());
		builder.authorities(user.getRole());
		
		return builder.build();
		
	}
	
	public boolean isValidLogin(String login) {
		
		if(loadUserByUsername(login) == null)
			return false;
		
		return login == loadUserByUsername(login).getUsername() ? true : false;
		
	}

	public boolean isValidLoginPassword(String login, String password) {
		
		if(loadUserByUsername(login) == null)
			return false;
		
		return login == loadUserByUsername(login).getUsername() && password == loadUserByUsername(login).getPassword() ? true : false;
		
	}
	
}
