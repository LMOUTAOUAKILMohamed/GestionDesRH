package ma.ac.emi.info.etudiant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.querydsl.core.types.Predicate;

@Controller
public class EtudiantArchiveController {
	
	@Autowired
	EtudiantArchiveService etudiantArchiveService;
	
	@GetMapping("/archive")
	public String allEtudiants(Model model) {
		model.addAttribute("minPromotion", etudiantArchiveService.getMinPromotion() != null ? etudiantArchiveService.getMinPromotion() : 0);
		model.addAttribute("maxPromotion", etudiantArchiveService.getMaxPromotion() != null ? etudiantArchiveService.getMaxPromotion() : 0);
		model.addAttribute("type", "archive");
		return "etudiants";
	}
	
	@GetMapping("/archiver")
	public String archiver(Model model, @RequestParam("promotion") int promotion) {
		etudiantArchiveService.archiver(promotion);
		return "redirect:/archive";
	}
	
	@PostMapping("/ModifierEtudiantArchive")
	public String modifierEtudiant(EtudiantArchive e) {
		etudiantArchiveService.modifierEtudiant(e);
		return "redirect:/archive";
	}
	
	@GetMapping("/ModifierEtudiantArchive")
	public String ModifierEtudiant(Model model, @RequestParam("id") Long id) {
		EtudiantArchive e = etudiantArchiveService.getOne(id);
		model.addAttribute("type", "archive");
		model.addAttribute("etudiant", e);
		return "modifier_etudiant";
	}
	
	@GetMapping("/rechercherArchive")
	public @ResponseBody Page<EtudiantArchive> chercherEtudiantArchive(Model model,
            @QuerydslPredicate(root = EtudiantArchive.class) Predicate predicate,
            @RequestParam(value = "page", defaultValue="0") int page, @RequestParam(value = "size", defaultValue="10") int size) {
		
	    return etudiantArchiveService.findAll(predicate, PageRequest.of(page, size));
		
	}

}
