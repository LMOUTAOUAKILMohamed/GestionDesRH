package org.emi.GestionDesRH.personneTests;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ma.ac.emi.info.enseignantPermanent.EnseignantPermanent;
import ma.ac.emi.info.enseignantPermanent.EnseignantPermanentRepository;
import ma.ac.emi.info.enums.Echelle;
import ma.ac.emi.info.enums.Grade;
import ma.ac.emi.info.enums.Sexe;
import ma.ac.emi.info.personne.DetailsUtilisateurService;

public class DetailsUtilisateurServiceTest {
	
	String identifier = "admin";
	String password = "123456";

	@Test
	public void testIsCorrectLogin() {
		
		EnseignantPermanent e = new EnseignantPermanent(); e.setNom("admin"); e.setPrenom("admin"); e.setEmailProfessionnel("admin@emi.ac.ma"); e.setGrade(Grade.PH);
		e.setSexe(Sexe.M); e.setEchelle(Echelle.ECHELLE_10); e.setRole("ADMIN"); e.setMotDePasse("123456");
		
		EnseignantPermanentRepository enseignantPermanentRepositoryMock = mock(EnseignantPermanentRepository.class);
		when(enseignantPermanentRepositoryMock.findByEmailProfessionnel(identifier)).thenReturn(e);
		DetailsUtilisateurService detailsUtilisateurService = new DetailsUtilisateurService(enseignantPermanentRepositoryMock);

		assertEquals(true , detailsUtilisateurService.isValidLogin(identifier));
		assertEquals(false , detailsUtilisateurService.isValidLogin("NonReconnu"));

	}
	
	@Test
	public void testIsCorrectLoginPassword() {
		
		EnseignantPermanent e = new EnseignantPermanent();
		e.setNom("admin"); e.setPrenom("admin"); e.setEmailProfessionnel("admin@emi.ac.ma"); e.setGrade(Grade.PH); e.setSexe(Sexe.M);
		e.setEchelle(Echelle.ECHELLE_10); e.setRole("ADMIN"); e.setMotDePasse("123456");
		
		EnseignantPermanentRepository enseignantPermanentRepositoryMock = mock(EnseignantPermanentRepository.class);
		when(enseignantPermanentRepositoryMock.findByEmailProfessionnel(identifier)).thenReturn(e);
		DetailsUtilisateurService detailsUtilisateurService = new DetailsUtilisateurService(enseignantPermanentRepositoryMock);
		
		assertEquals(true , detailsUtilisateurService.isValidLoginPassword(identifier, password));
		assertEquals(false , detailsUtilisateurService.isValidLoginPassword(identifier, "NonReconnu"));
		assertEquals(false , detailsUtilisateurService.isValidLoginPassword("NonReconnu", password));
		assertEquals(false , detailsUtilisateurService.isValidLoginPassword("NonReconnu", "NonReconnu"));

	}

}
