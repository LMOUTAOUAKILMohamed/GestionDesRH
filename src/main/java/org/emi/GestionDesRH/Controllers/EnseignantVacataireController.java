package org.emi.GestionDesRH.Controllers;

import javax.validation.Valid;

import org.emi.GestionDesRH.Services.EnseignantVacataireService;
import org.emi.GestionDesRH.entities.EnseignantVacataire;
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
public class EnseignantVacataireController {
	
	@Autowired
	EnseignantVacataireService enseignantVacataireService;
	
	@GetMapping("/enseignantsVacataires")
	public String listeEnseignants(Model model, @RequestParam(defaultValue="0") int page, @RequestParam(defaultValue="2") int size){
		model.addAttribute("type", "EV");
		return "enseignants";
	}
	
	@GetMapping("/rechercherEnseignantsVacataires")
	public @ResponseBody Page<EnseignantVacataire> chercherEnseignantVacataire(Model model,
            @QuerydslPredicate(root = EnseignantVacataire.class) Predicate predicate,
            Pageable pageable) {
		System.out.println(pageable);
	    return enseignantVacataireService.findAll(predicate, pageable);
		
	}
	
	@PostMapping(value="/AjouterEnseignantVacataire")
	public String AjouterEnseignantVacataire(@Valid EnseignantVacataire ev, BindingResult bindingResult) {
		
		if( bindingResult.hasErrors())
			System.out.println(bindingResult);
		
		enseignantVacataireService.AjouterEnseignantVacataire(ev);
		return "redirect:/enseignantsVacataires";
	}
	
	@GetMapping("/ModifierEnseignantVacataire")
	public String ModifierEnseignantVacataire(Model model, @RequestParam("id") Long id) {
		model.addAttribute("enseignant", enseignantVacataireService.getOne(id));
		model.addAttribute("type", "EV");
		return "modifier_enseignant";
	}
	
	@PostMapping("/ModifierEnseignantVacataire")
	public String modifierEnseignantVacataire(@Valid EnseignantVacataire ev, BindingResult bindingResult) {
		if( bindingResult.hasErrors())
			System.out.println(bindingResult);
		
		enseignantVacataireService.modifierEnseignantVacataire(ev);
		
		return "redirect:/enseignantsVacataires";
	}
	
}
