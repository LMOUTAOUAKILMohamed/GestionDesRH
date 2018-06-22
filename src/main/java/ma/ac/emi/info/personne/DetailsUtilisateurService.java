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
	
	@Override
	public UserDetails loadUserByUsername(String emailprofessionnel) throws UsernameNotFoundException{
		
		UserBuilder builder = null;
		
		System.out.println("alllll = " + enseignantPermanentRepository.findAll());
		
		Personne user = enseignantPermanentRepository.findByEmailProfessionnel(emailprofessionnel);
		System.out.println("user = " + user);
		if(user == null)
			user = enseignantVacataireRepository.findByEmailProfessionnel(emailprofessionnel);
		if(user == null)
			user = etudiantRepository.findByEmailProfessionnel(emailprofessionnel);
		if(user == null)
			throw new UsernameNotFoundException("Aucun utilisateur existe avec l'email " + emailprofessionnel );
		
		System.out.println("role = " + user.getRole());
		System.out.println("alllll = " + enseignantPermanentRepository.findAll());
		builder = User.withUsername(emailprofessionnel);
		builder.password(user.getMotDePasse());
		builder.authorities(user.getRole());
		
		return builder.build();
		
	}
}
