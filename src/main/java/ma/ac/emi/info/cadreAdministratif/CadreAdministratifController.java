package ma.ac.emi.info.cadreAdministratif;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class CadreAdministratifController {
	
	@Autowired
	CadreAdministratifService cadreAdministratifService;
	
	@GetMapping("/cadresAdministratifs")
	public String listeCadresAdministratifs(Model model) {
		return "cadresAdministratifs";
	}
	
	@GetMapping("/rechercherCadresAdministratifs")
	public @ResponseBody Page<CadreAdministratif> chercherCadresAdministratifs(Model model, Pageable pageable){
		return cadreAdministratifService.findAll(pageable);
	}
	
	@PostMapping("/AjouterCadreAdministratif")
	public String ajouterCadreAdministratif(@Valid CadreAdministratif c, BindingResult bindingResult) {
				
		if( bindingResult.hasErrors())
			System.out.println(bindingResult);
		
		cadreAdministratifService.ajouterCadreAdministratif(c);
		return "redirect:/cadresAdministratifs";
	}

	@GetMapping("/ModifierCadreAdministratif")
	public String ModifierCadreAdministratif(Model model, @RequestParam("id") Long id) {
		CadreAdministratif c = cadreAdministratifService.getOne(id);
		model.addAttribute("cadreAdministratif", c);
		return "modifier_cadreAdministratif";
	}
	
	@PostMapping("/ModifierCadreAdministratif")
	public String modifierCadreAdministratif(@Valid CadreAdministratif c, BindingResult bindingResult) {
		
		if( bindingResult.hasErrors())
			System.out.println(bindingResult);
		
		cadreAdministratifService.modifierCadreAdministratif(c);
		return "redirect:/cadresAdministratifs";
	}
	
}
