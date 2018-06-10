package ma.ac.emi.info.enseignantPermanent;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;

@Service
@Transactional
public class EnseignantPermanentService {

	@Autowired
	EnseignantPermanentRepository enseignantPermanentRepository;
		
	@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
		
	public EnseignantPermanent AjouterEnseignantPermanent( EnseignantPermanent ep ) {
		ep.setMotDePasse(bCryptPasswordEncoder.encode(ep.getMotDePasse()));
		ep.setRole("ENSEIGNANT_P");
		EnseignantPermanent e = enseignantPermanentRepository.save(ep);
		return e;
	}
	
	public EnseignantPermanent modifierEnseignantPermanent(EnseignantPermanent ep) {
		if(ep.getMotDePasse().equals(""))
			ep.setMotDePasse(enseignantPermanentRepository.getPasswordById(ep.getId()));
		
		return enseignantPermanentRepository.save(ep);
	}
	
	public Page<EnseignantPermanent> listeEnseignantsPermanents(int page, int size){
		return enseignantPermanentRepository.findAll(PageRequest.of(page, size));
	}
	
	public List<EnseignantPermanent> findAll(){
		return enseignantPermanentRepository.findAll();
	}
	
	public Iterable<EnseignantPermanent> findAll(BooleanExpression build) {
		return enseignantPermanentRepository.findAll(build);
	}
	
	public EnseignantPermanent getOne(Long id) {
		return enseignantPermanentRepository.getOne(id);
	}
	
	public Page<EnseignantPermanent> findAll(Predicate predicate, Pageable pageable) {
		if(predicate == null)
			return enseignantPermanentRepository.findAll(pageable);
		return enseignantPermanentRepository.findAll(predicate, pageable);
	}
	
}
