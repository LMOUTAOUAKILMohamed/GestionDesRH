package ma.ac.emi.info.personne;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ma.ac.emi.info.cadreAdministratif.CadreAdministratif;
import ma.ac.emi.info.enseignantPermanent.EnseignantPermanent;
import ma.ac.emi.info.enseignantPermanent.EnseignantPermanentService;
import ma.ac.emi.info.enseignantVacataire.EnseignantVacataire;
import ma.ac.emi.info.enseignantVacataire.EnseignantVacataireService;
import ma.ac.emi.info.etudiant.Etudiant;

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
