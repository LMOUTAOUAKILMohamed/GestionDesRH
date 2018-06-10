package ma.ac.emi.info.cadreAdministratif;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import ma.ac.emi.info.enumConverters.FonctionConverter;
import ma.ac.emi.info.enums.Fonction;
import ma.ac.emi.info.enums.Grade;
import ma.ac.emi.info.enums.Sexe;
import ma.ac.emi.info.personne.Personne;

@Entity
@Table(name="CADRES_ADMINISTRATIFS")
public class CadreAdministratif extends Personne{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotNull
	@Column(name = "DATE_AFFECTATION")
	protected int dateAffectation;
	
	@NotNull
	@Column(name = "FONCTION", length = 1)
	@Convert(converter = FonctionConverter.class)
	protected Fonction fonction;
	
	@NotNull
	@Column(name = "GRADE", length = 3)
	protected Grade grade;

	public CadreAdministratif() {
		super();
	}

	public CadreAdministratif(Long id,
			@NotNull @Size(max = 30, message = "Un nom ne peut pas depasser 30 caractères!") String nom,
			@NotNull @Size(max = 20, message = "Un prenom ne peut pas depasser 20 caractères!") String prenom,
			@NotNull @Size(max = 8, message = "Un CIN ne peut pas depasser 8 caractères!") String cIN,
			@NotNull Date dateNaissance, @NotNull Sexe sexe,
			@NotNull @Size(max = 15, message = "Un numéro de téléphone ne peut pas depasser 15 caractères!") String telephone,
			@Size(max = 40, message = "Un email ne peut pas depasser 40 caractères!") String emailProfessionnel,
			@Size(max = 40, message = "Un email ne peut pas depasser 40 caractères!") String emailPersonnel,
			@Size(max = 40, message = "Un nom et prenom en arabe ne peut pas depasser 40 caractères!") String nomPrenomArabe,
			@Size(max = 100, message = "Une adresse ne peut pas depasser 100 caractères!") String adresse,
			String motDePasse, @NotNull int dateAffectation, @NotNull Fonction fonction, @NotNull Grade grade) {
		super(id, nom, prenom, cIN, dateNaissance, sexe, telephone, emailProfessionnel, emailPersonnel, nomPrenomArabe,
				adresse, motDePasse);
		this.dateAffectation = dateAffectation;
		this.fonction = fonction;
		this.grade = grade;
	}

	public int getDateAffectation() {
		return dateAffectation;
	}

	public void setDateAffectation(int dateAffectation) {
		this.dateAffectation = dateAffectation;
	}

	public Fonction getFonction() {
		return fonction;
	}

	public void setFonction(Fonction fonction) {
		this.fonction = fonction;
	}

	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}
	
}
