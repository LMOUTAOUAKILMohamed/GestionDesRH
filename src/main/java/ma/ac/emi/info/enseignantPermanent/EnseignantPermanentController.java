package ma.ac.emi.info.enseignantPermanent;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.querydsl.core.types.Predicate;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class EnseignantPermanentController {
	
	@Autowired
	EnseignantPermanentService enseignantPermanentService;
	
	@GetMapping("/enseignantsPermanents")
	public String listeEnseignants(Model model, @RequestParam(defaultValue="0") int page, @RequestParam(defaultValue="2") int size){
		model.addAttribute("type", "EP");
		return "enseignants";
	}
	
	@GetMapping("/rechercherEnseignantsPermanents")
	public @ResponseBody Page<EnseignantPermanent> chercherEnseignantVacataire(Model model,
            @QuerydslPredicate(root = EnseignantPermanent.class) Predicate predicate,
            Pageable pageable) {
		
	    return enseignantPermanentService.findAll(predicate, pageable);
		
	}
	
	@PostMapping(value="/AjouterEnseignantPermanent")
	public String AjouterEnseignantPermanent(@Valid EnseignantPermanent ep, BindingResult bindingResult) {
		
		if( bindingResult.hasErrors())
			System.out.println(bindingResult);
		
		enseignantPermanentService.AjouterEnseignantPermanent(ep);
		
		return "redirect:/enseignantsPermanents";
	}
	
	@GetMapping("/ModifierEnseignantPermanent")
	public String ModifierEnseignantPermanent(Model model, @RequestParam("id") Long id) {
		EnseignantPermanent e = enseignantPermanentService.getOne(id);
		model.addAttribute("type", "EP");
		model.addAttribute("enseignant", e);
		return "modifier_enseignant";
	}
	
	@PostMapping("/ModifierEnseignantPermanent")
	public String modifierEnseignantPermanent(@Valid EnseignantPermanent ep, BindingResult bindingResult) {
		if( bindingResult.hasErrors())
			System.out.println(bindingResult);
		
		enseignantPermanentService.modifierEnseignantPermanent(ep);
		
		return "redirect:/enseignantsPermanents";
	}
	
	@GetMapping("SupprimerEP")
	public String supprimerEV(@RequestParam("id") Long id) {
		enseignantPermanentService.supprimer(id);
		return "redirect:/enseignantsPermanents";
	}
	
}
