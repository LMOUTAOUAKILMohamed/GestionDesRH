package org.emi.GestionDesRH.Services;

import java.util.List;
import org.emi.GestionDesRH.Repositories.EnseignantVacataireRepository;
import org.emi.GestionDesRH.entities.EnseignantVacataire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;

@Service
public class EnseignantVacataireService {

	@Autowired
	EnseignantVacataireRepository enseignantVacataireRepository;
	
	@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public EnseignantVacataire AjouterEnseignantVacataire(EnseignantVacataire ev) {
		ev.setMotDePasse(bCryptPasswordEncoder.encode(ev.getMotDePasse()));
		ev.setRole("ENSEIGNANT_V");
		return enseignantVacataireRepository.save(ev);
	}
	
	public EnseignantVacataire modifierEnseignantVacataire(EnseignantVacataire ev) {
		if(ev.getMotDePasse().equals(""))
			ev.setMotDePasse(enseignantVacataireRepository.getPasswordById(ev.getId()));
		
		return enseignantVacataireRepository.save(ev);
	}
	
	public Page<EnseignantVacataire> listeEnseignantsVacataires(int page, int size){
		return enseignantVacataireRepository.findAll(PageRequest.of(page, size));
	}
	
	public List<EnseignantVacataire> findAll(){
		return enseignantVacataireRepository.findAll();
	}
	
	public Iterable<EnseignantVacataire> findAll(BooleanExpression build) {
		return enseignantVacataireRepository.findAll(build);
	}
	
	public EnseignantVacataire getOne(Long id) {
		return enseignantVacataireRepository.getOne(id);
	}
	
	public Page<EnseignantVacataire> findAll(Predicate predicate, Pageable pageable){
		if(predicate == null)
			return enseignantVacataireRepository.findAll(pageable);
		return enseignantVacataireRepository.findAll(predicate, pageable);
	}
	
}
