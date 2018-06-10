package ma.ac.emi.info.etudiant;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.text.ParseException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.querydsl.core.types.Predicate;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class EtudiantController {
	
	@Autowired
	EtudiantService etudiantService;
	
	@Value("${upload.images}") 
	private String shemin;
	
	@RequestMapping(path="/AjouterEtudiant", method = RequestMethod.POST)
	public String AjouterEtudiant(Etudiant e, @RequestParam("imageEtudiant") MultipartFile multipartFile) throws IOException {
			
			Etudiant ei = etudiantService.ajouterEtudiant(e);
			
			if (ei != null) 
			{
				//Part partImageEtudiant = httpServletRequest.getPart("imageEtudiant");
				
				if(/*partImageEtudiant*/!multipartFile.isEmpty()) 
				{
					System.out.println(multipartFile.isEmpty());
					System.out.println("passeee");
					File file = new File(shemin, String.valueOf(ei.getId()) + ".jpg");
					
					try (InputStream input = multipartFile.getInputStream()) {
					    Files.copy(input, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
					}
					etudiantService.affecterPhoto(ei.getId());
				}
			}
		
		return "redirect:/allEtudiants";
	}
	
	@PostMapping("/modifierEtudiant")
	public String modifierEtudiant(Etudiant e) {
		etudiantService.modifierEtudiant(e);
		return "redirect:/allEtudiants";
	}
	
	/*@GetMapping("/etudiants")
	public String listEtudiants(Model model, @RequestParam(defaultValue="0") int page, @RequestParam(defaultValue="2") int size){
		model.addAttribute("liste", etudiantService.listeEtudiants(page, size));
		model.addAttribute("pageCourante", page);
		return "liste";
	}*/
	
	@GetMapping("/ModifierEtudiant")
	public String ModifierEtudiant(Model model, @RequestParam("id") Long id) {
		Etudiant e = etudiantService.getOne(id);
		model.addAttribute("type", "etudiant");
		model.addAttribute("etudiant", e);
		return "modifier_etudiant";
	}
	
	@GetMapping("/rechercher")
	public @ResponseBody Page<Etudiant> chercherEtudiant(Model model,
            @QuerydslPredicate(root = Etudiant.class) Predicate predicate,
            @RequestParam(value = "page", defaultValue="0") int page, @RequestParam(value = "size", defaultValue="10") int size) {
		
	    return etudiantService.findAll(predicate, PageRequest.of(page, size));
		
	}
	
	@GetMapping("/listEtudiants")
	public @ResponseBody Page<Etudiant> listEtudiantsJSON(Model model, @RequestParam(value = "page", defaultValue="0") int page, @RequestParam(value = "size",defaultValue="10") int size){
		
		return etudiantService.listeEtudiants(page, size);
		
	}
	
	@GetMapping("/etudiants")
	public String allEtudiants(Model model) {
		model.addAttribute("minPromotion", etudiantService.getMinPromotion());
		model.addAttribute("maxPromotion", etudiantService.getMaxPromotion());
		model.addAttribute("type", "etudiants");
		return "etudiants";
	}
	
	@PostMapping("/importExcelFile")
	public String importExcelFile(Model model, @RequestParam("ExcelFile") MultipartFile multipartFile ) throws IOException, ParseException {
		etudiantService.processExcelSheet(multipartFile.getInputStream());
		return "etudiants";
	}
	

}
