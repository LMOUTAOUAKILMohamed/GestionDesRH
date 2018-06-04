package org.emi.GestionDesRH.Services;

import org.emi.GestionDesRH.Repositories.EnseignantPermanentRepository;
import org.emi.GestionDesRH.Repositories.EnseignantVacataireRepository;
import org.emi.GestionDesRH.Repositories.EtudiantRepository;
import org.emi.GestionDesRH.entities.Personne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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
		
		Personne user = enseignantPermanentRepository.findByEmailProfessionnel(emailprofessionnel);
		if(user == null)
			user = enseignantVacataireRepository.findByEmailProfessionnel(emailprofessionnel);
		if(user == null)
			user = etudiantRepository.findByEmailProfessionnel(emailprofessionnel);
		if(user == null)
			throw new UsernameNotFoundException("Aucun utilisateur existe avec l'email " + emailprofessionnel );
		
		System.out.println(user);
		
		builder = User.withUsername(emailprofessionnel);
		builder.password(user.getMotDePasse());
		builder.authorities(user.getRole());
		
		return builder.build();
		
	}
}
