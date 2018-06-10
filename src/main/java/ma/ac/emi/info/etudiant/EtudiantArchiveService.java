package ma.ac.emi.info.etudiant;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.querydsl.core.types.Predicate;

@Service
public class EtudiantArchiveService {

	@Autowired
	EtudiantArchiveRepository etudiantArchiveRepository;
	
	@Autowired
	EtudiantService etudiantService;
	
	public void archiver(int promotion) {
		
		List<Etudiant> listEtudiants = etudiantService.findByPromotion(promotion);
		
		List<EtudiantArchive> listEtudiantsAArchive = new ArrayList<>();
		
		listEtudiants.forEach( e -> {
			listEtudiantsAArchive.add( new EtudiantArchive(e));
		});
		System.out.println("hiii");
		etudiantArchiveRepository.saveAll(listEtudiantsAArchive);
		
		etudiantService.supprimerListeEtudiants(listEtudiants);
		
	}
	
	public Page<EtudiantArchive> findAll(Predicate predicate, PageRequest of) {
		if( predicate != null )
			return etudiantArchiveRepository.findAll(predicate, of);
		return etudiantArchiveRepository.findAll(of);
	}
	
	public Integer getMaxPromotion() {
		return etudiantArchiveRepository.getMaxPromotion();
	}
	
	public Integer getMinPromotion() {
		return etudiantArchiveRepository.getMinPromotion();
	}

	public void modifierEtudiant(EtudiantArchive e) {
		e.setPromotion(e.getDateInscription() + 3);
		etudiantArchiveRepository.save(e);
	}

	public EtudiantArchive getOne(Long id) {
		// TODO Auto-generated method stub
		return etudiantArchiveRepository.getOne(id);
	}
	
}
