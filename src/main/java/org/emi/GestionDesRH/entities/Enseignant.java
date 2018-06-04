package org.emi.GestionDesRH.entities;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.emi.GestionDesRH.enums.Echelle;
import org.emi.GestionDesRH.enums.Grade;
import org.emi.GestionDesRH.enums.Sexe;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TYPE_ENSEIGNANT", discriminatorType = DiscriminatorType.STRING)
@Table(name="ENSEIGNANTS")
public class Enseignant extends Personne{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "DATE_AFFECTATION")
	protected int dateAffectation;
	
	@NotNull
	@Column(name = "ECHELLE")
	protected Echelle echelle;
	
	@NotNull
	@Column(name = "GRADE", length = 3)
	protected Grade grade;

	public Enseignant() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected Enseignant(Long id,
			@NotNull @Size(max = 30, message = "Un nom ne peut pas depasser 30 caractères!") String nom,
			@NotNull @Size(max = 20, message = "Un prenom ne peut pas depasser 20 caractères!") String prenom,
			@NotNull @Size(max = 8, message = "Un CIN ne peut pas depasser 8 caractères!") String cIN,
			@NotNull Date dateNaissance, @NotNull Sexe sexe,
			@NotNull @Size(max = 15, message = "Un numéro de téléphone ne peut pas depasser 15 caractères!") String telephone,
			@Size(max = 40, message = "Un email ne peut pas depasser 40 caractères!") String emailProfessionnel,
			@Size(max = 40, message = "Un email ne peut pas depasser 40 caractères!") String emailPersonnel,
			@Size(max = 40, message = "Un nom et prenom en arabe ne peut pas depasser 40 caractères!") String nomPrenomArabe,
			@Size(max = 100, message = "Une adresse ne peut pas depasser 100 caractères!") String adresse,
			String motDePasse, @NotNull int dateAffectation, @NotNull Echelle echelle, @NotNull Grade grade) {
		super(id, nom, prenom, cIN, dateNaissance, sexe, telephone, emailProfessionnel, emailPersonnel, nomPrenomArabe,
				adresse, motDePasse);
		this.dateAffectation = dateAffectation;
		this.echelle = echelle;
		this.grade = grade;
	}

	public int getDateAffectation() {
		return dateAffectation;
	}

	public void setDateAffectation(int dateAffectation) {
		this.dateAffectation = dateAffectation;
	}

	public Echelle getEchelle() {
		return echelle;
	}

	public void setEchelle(Echelle echelle) {
		this.echelle = echelle;
	}

	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	@Override
	public String toString() {
		return "Enseignant [dateAffectation=" + dateAffectation + ", echelle=" + echelle + ", grade=" + grade + ", id="
				+ id + ", role=" + role + ", nom=" + nom + ", prenom=" + prenom + ", CIN=" + CIN + ", dateNaissance="
				+ dateNaissance + ", sexe=" + sexe + ", telephone=" + telephone + ", emailProfessionnel="
				+ emailProfessionnel + ", emailPersonnel=" + emailPersonnel + ", nomPrenomArabe=" + nomPrenomArabe
				+ ", adresse=" + adresse + "]";
	}
	
	//@Column(name = "TYPE_ENSEIGNANT" , insertable=false, updatable=false)
	//protected String type;
	
	

}
