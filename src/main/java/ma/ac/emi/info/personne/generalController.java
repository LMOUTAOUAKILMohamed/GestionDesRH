package ma.ac.emi.info.personne;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ma.ac.emi.info.cadreAdministratif.CadreAdministratif;
import ma.ac.emi.info.enseignantPermanent.EnseignantPermanent;
import ma.ac.emi.info.enseignantPermanent.EnseignantPermanentRepository;
import ma.ac.emi.info.enseignantPermanent.EnseignantPermanentService;
import ma.ac.emi.info.enseignantVacataire.EnseignantVacataire;
import ma.ac.emi.info.enseignantVacataire.EnseignantVacataireService;
import ma.ac.emi.info.enums.Echelle;
import ma.ac.emi.info.enums.Grade;
import ma.ac.emi.info.enums.Sexe;
import ma.ac.emi.info.etudiant.Etudiant;

@Controller
public class generalController {
	
	@Autowired
	EnseignantPermanentService enseignantPermanentService;
	
	@Autowired
	EnseignantVacataireService enseignantVacataireService;
	
	@Autowired
	EnseignantPermanentRepository enseignantPermanentRepository;
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login(Principal principal) {
		
        return principal == null ?  "login" : "redirect:/"; 
    }
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
	public String index(Model model) {
		return "index";
	}
	
	//@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = { "/inscription" }, method = RequestMethod.GET)
    public String inscription(Model model) {
		model.addAttribute("etudiant", new Etudiant());
		model.addAttribute("enseignantP", new EnseignantPermanent());
		model.addAttribute("enseignantV", new EnseignantVacataire());
		model.addAttribute("cadreAdministratif", new CadreAdministratif());
		
		/*EnseignantPermanent e = new EnseignantPermanent();
		e.setNom("admin");
		e.setPrenom("admin");
		e.setEmailProfessionnel("admin@emi.ac.ma");
		e.setGrade(Grade.PH);
		e.setSexe(Sexe.M);
		e.setEchelle(Echelle.ECHELLE_10);
		e.setRole("ADMIN");
		e.setMotDePasse(new BCryptPasswordEncoder().encode("adminadmin"));
		enseignantPermanentRepository.save(e);*/
		
        return "inscription";
    }

}
