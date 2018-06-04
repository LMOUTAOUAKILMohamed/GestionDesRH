package org.emi.GestionDesRH.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.emi.GestionDesRH.enums.Echelle;
import org.emi.GestionDesRH.enums.Grade;
import org.emi.GestionDesRH.enums.Sexe;

@Entity
@DiscriminatorValue("EV")
public class EnseignantVacataire extends Enseignant{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column( name = "ETABLISSEMENT" )
	@Size( max = 40 , message = "Un nom d'etablissement ne peut pas depasser 40 caractères!")
	private String etablissement;
	
	@Column( name = "ETAT_PAIEMENT" )
	private boolean etatPaiement;

	public EnseignantVacataire() {
		super();
	}

	public EnseignantVacataire(Long id,
			@NotNull @Size(max = 30, message = "Un nom ne peut pas depasser 30 caractères!") String nom,
			@NotNull @Size(max = 20, message = "Un prenom ne peut pas depasser 20 caractères!") String prenom,
			@NotNull @Size(max = 8, message = "Un CIN ne peut pas depasser 8 caractères!") String cIN,
			@NotNull Date dateNaissance, @NotNull Sexe sexe,
			@NotNull @Size(max = 15, message = "Un numéro de téléphone ne peut pas depasser 15 caractères!") String telephone,
			@Size(max = 40, message = "Un email ne peut pas depasser 40 caractères!") String emailProfessionnel,
			@Size(max = 40, message = "Un email ne peut pas depasser 40 caractères!") String emailPersonnel,
			@Size(max = 40, message = "Un nom et prenom en arabe ne peut pas depasser 40 caractères!") String nomPrenomArabe,
			@Size(max = 100, message = "Une adresse ne peut pas depasser 100 caractères!") String adresse,
			String motDePasse, @NotNull int dateAffectation, @NotNull Echelle echelle, @NotNull Grade grade,
			@Size(max = 40, message = "Un nom d'etablissement ne peut pas depasser 40 caractères!") String etablissement,
			boolean etatPaiement) {
		super(id, nom, prenom, cIN, dateNaissance, sexe, telephone, emailProfessionnel, emailPersonnel, nomPrenomArabe,
				adresse, motDePasse, dateAffectation, echelle, grade);
		this.etablissement = etablissement;
		this.etatPaiement = etatPaiement;
	}



	public String getEtablissement() {
		return etablissement;
	}

	public void setEtablissement(String etablissement) {
		this.etablissement = etablissement;
	}

	public boolean getEtatPaiement() {
		return etatPaiement;
	}

	public void setEtatPaiement(boolean etatPaiement) {
		this.etatPaiement = etatPaiement;
	}

	@Override
	public String toString() {
		return "EnseignantVacataire [etablissement=" + etablissement + ", etatPaiement=" + etatPaiement
				+ ", dateAffectaion=" + dateAffectation + ", echelle=" + echelle + ", grade=" + grade + ", id=" + id
				+ ", nom=" + nom + ", prenom=" + prenom + ", CIN=" + CIN + ", dateNaissance=" + dateNaissance
				+ ", sexe=" + sexe + ", telephone=" + telephone + ", emailProfessionnel=" + emailProfessionnel
				+ ", emailPersonnel=" + emailPersonnel + ", nomPrenomArabe=" + nomPrenomArabe + ", adresse=" + adresse
				+ ", motDePasse=" + motDePasse + "]";
	}

}
