package ma.ac.emi.info.cadreAdministratif;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CadreAdministratifService {

	@Autowired
	CadreAdministratifRepository cadreAdministratifRepository;
	
	public CadreAdministratif ajouterCadreAdministratif(CadreAdministratif c) {
		return cadreAdministratifRepository.save(c);
	}
	
	public Page<CadreAdministratif> listeEtudiants(int page, int size) {
		return cadreAdministratifRepository.findAll(PageRequest.of(page, size));
	}	
	
	public CadreAdministratif modifierCadreAdministratif(CadreAdministratif c) {
		
		if(c.getMotDePasse().equals(""))
			c.setMotDePasse(getPasswordById(c.getId()));
		
		return cadreAdministratifRepository.save(c);
	}
	
	public String getPasswordById(Long id) {
		return cadreAdministratifRepository.getPasswordById(id);
	}

	public Page<CadreAdministratif> findAll(Pageable pageable) {
		return cadreAdministratifRepository.findAll(pageable);
	}
	
	public CadreAdministratif getOne(Long id) {
		return cadreAdministratifRepository.getOne(id);
	}
	
}
