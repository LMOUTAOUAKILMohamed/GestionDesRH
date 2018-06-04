package org.emi.GestionDesRH.Controllers;

import java.security.Principal;
import org.emi.GestionDesRH.Services.EnseignantPermanentService;
import org.emi.GestionDesRH.Services.EnseignantVacataireService;
import org.emi.GestionDesRH.entities.CadreAdministratif;
import org.emi.GestionDesRH.entities.EnseignantPermanent;
import org.emi.GestionDesRH.entities.EnseignantVacataire;
import org.emi.GestionDesRH.entities.Etudiant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class generalController {
	
	@Autowired
	EnseignantPermanentService enseignantPermanentService;
	
	@Autowired
	EnseignantVacataireService enseignantVacataireService;
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login(Principal principal) {
		System.out.println(principal);
        return principal == null ?  "login" : "redirect:/"; 
    }
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
	public String index(Model model) {
		return "index";
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = { "/inscription" }, method = RequestMethod.GET)
    public String inscription(Model model) {
		model.addAttribute("etudiant", new Etudiant());
		model.addAttribute("enseignantP", new EnseignantPermanent());
		model.addAttribute("enseignantV", new EnseignantVacataire());
		model.addAttribute("cadreAdministratif", new CadreAdministratif());
        return "inscription";
    }

}
