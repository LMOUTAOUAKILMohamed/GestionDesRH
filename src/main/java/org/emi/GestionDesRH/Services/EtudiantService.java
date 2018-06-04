package org.emi.GestionDesRH.Services;

import java.io.IOException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.emi.GestionDesRH.Repositories.EtudiantRepository;
import org.emi.GestionDesRH.entities.Etudiant;
import org.emi.GestionDesRH.enumConverters.SexeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;

@Service
public class EtudiantService {

	@Autowired
	EtudiantRepository etudiantRepository;
	
	@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy",Locale.FRENCH);
	
	SexeConverter sexeConverter = new SexeConverter();
	
	public Etudiant ajouterEtudiant(Etudiant e) {
		e.setMotDePasse(bCryptPasswordEncoder.encode(e.getMotDePasse()));
		e.setRole("ETUDIANT");
		e.setPromotion(e.getDateInscription() + 3);
		return etudiantRepository.save(e);
	}
	
	public Etudiant modifierEtudiant(Etudiant e) {
		if(e.getMotDePasse() == null)
			e.setMotDePasse(getPasswordById(e.getId()));
		e.setPromotion(e.getDateInscription() + 3);
		return etudiantRepository.save(e);
	}
	
	public Page<Etudiant> listeEtudiants(int page, int size) {
		return etudiantRepository.findAll(PageRequest.of(page, size));
	}
	
	public int affecterPhoto(Long etudiantId) {
		return etudiantRepository.affecterPhoto(etudiantId);
	}

	public Page<Etudiant> findAll(BooleanExpression build, int page, int size) {
		return etudiantRepository.findAll(build, PageRequest.of(page, size));
	}
	
	public List<Etudiant> findAll(){
		return etudiantRepository.findAll();
	}
	
	public String getPasswordById(Long id) {
		return etudiantRepository.getPasswordById(id);
	}
	
	public Integer getMaxPromotion() {
		return etudiantRepository.getMaxPromotion();
	}
	
	public Integer getMinPromotion() {
		return etudiantRepository.getMinPromotion();
	}
	
	public List<Etudiant> processExcelSheet(/*MultipartFile multipartFile*/ InputStream stream) throws IOException, ParseException{
		
		//InputStream stream = multipartFile.getInputStream();
		List<Etudiant> etudiants = new ArrayList<>();
		@SuppressWarnings("resource")
		XSSFWorkbook workbook = new XSSFWorkbook(stream);
		XSSFSheet sheet = workbook.getSheetAt(0); 
		Iterator<Row> rowIterator = sheet.iterator();
		Etudiant e = new Etudiant();
		
		
		while (rowIterator.hasNext()) {
            // Skip read heading
			Row row = rowIterator.next();
			if (row.getRowNum() < 5) {
               continue;
			}
			if(row.getCell(2).getStringCellValue() == "")
				break;
			
			e.setMatricule(new Double(row.getCell(2).toString()).longValue());
			e.setNom(row.getCell(4).toString());
            e.setPrenom(row.getCell(5).toString());
            System.out.println("ouiiiiiiiiii");
            /*e.setMatricule(new Double(row.getCell(0).getNumericCellValue()).longValue() );
            e.setNom(row.getCell(1).toString());
            e.setPrenom(row.getCell(2).toString());
            e.setSexe(sexeConverter.convertToEntityAttribute(row.getCell(3).toString()));
            e.setDateNaissance(formatter.parse(row.getCell(4).toString()));
            e.setAdresse(row.getCell(5).toString());
            e.setCNE(new Double(row.getCell(6).getNumericCellValue()).longValue());
            e.setCIN(row.getCell(7).toString());
            e.setNomPrenomArabe(row.getCell(8).toString());
            e.setTelephone(row.getCell(9).toString());
            //e.setDateInscription(formatter.parse(row.getCell(10).toString()));
            e.setDateInscription(new Double(row.getCell(10).getNumericCellValue()).intValue());*/
            
            etudiants.add(etudiantRepository.save(e));
            
       }
		return etudiants;
	}

	public Page<Etudiant> findAll(Predicate predicate, int pageNumber, int pageSize) {
		return etudiantRepository.findAll(predicate, PageRequest.of(pageNumber, pageSize));
	}

	public Etudiant getOne(Long id) {
		return etudiantRepository.getOne(id);
	}

	public Page<Etudiant> findAll(Predicate predicate, PageRequest of) {
		if( predicate != null )
			return etudiantRepository.findAll(predicate, of);
		return etudiantRepository.findAll(of);
	}
	
}