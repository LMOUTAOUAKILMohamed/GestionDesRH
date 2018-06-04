package org.emi.GestionDesRH.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.emi.GestionDesRH.enumConverters.SexeConverter;
import org.emi.GestionDesRH.enums.Sexe;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@MappedSuperclass
public abstract class Personne implements Serializable{
	
	/**
	 * 
	 */
	protected static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	protected Long id;
	
	@Column(name = "ROLE")
	protected String role;
	
	@NotNull
	@Size( max = 30 , message = "Un nom ne peut pas depasser 30 caractères!")
	@Column(name = "NOM")
	protected String nom;
	
	@NotNull
	@Size( max = 20 , message = "Un prenom ne peut pas depasser 20 caractères!")
	@Column(name = "PRENOM")
	protected String prenom;
	
	//@NotNull
	@Size( max = 8 , message = "Un CIN ne peut pas depasser 8 caractères!")
	@Column(name = "CIN")
	protected String CIN;
	
	//@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "DATE_NAISSANCE")
	@Temporal(TemporalType.DATE)
	protected Date dateNaissance;
	
	//@NotNull
	@Column(name = "SEXE")
	@Convert(converter = SexeConverter.class)
	protected Sexe sexe;
	
	//@NotNull
	@Size( max = 15 , message = "Un numéro de téléphone ne peut pas depasser 15 caractères!")
	@Column(name = "TELEPHONE")
	protected String telephone;
	
	@Size( max = 40 , message = "Un email ne peut pas depasser 40 caractères!")
	@Column(name = "EMAIL_PROFESSIONNEL")
	protected String emailProfessionnel;
	
	@Size( max = 40 , message = "Un email ne peut pas depasser 40 caractères!")
	@Column(name = "EMAIL_PERSONNEL")
	protected String emailPersonnel;
	
	@Size( max = 40 , message = "Un nom et prenom en arabe ne peut pas depasser 40 caractères!")
	@Column(name = "NOM_PRENOM_ARABE")
	protected String nomPrenomArabe;
	
	@Size( max=100 , message = "Une adresse ne peut pas depasser 100 caractères!")
	@Column(name = "ADRESSE")
	protected String adresse;
	
	@JsonIgnore
	@Column(name = "MOT_DE_PASSE")
	protected String motDePasse;

	public Personne(Long id,
			@NotNull @Size(max = 30, message = "Un nom ne peut pas depasser 30 caractères!") String nom,
			@NotNull @Size(max = 20, message = "Un prenom ne peut pas depasser 20 caractères!") String prenom,
			@NotNull @Size(max = 8, message = "Un CIN ne peut pas depasser 8 caractères!") String cIN,
			@NotNull Date dateNaissance, @NotNull Sexe sexe,
			@NotNull @Size(max = 15, message = "Un numéro de téléphone ne peut pas depasser 15 caractères!") String telephone,
			@Size(max = 40, message = "Un email ne peut pas depasser 40 caractères!") String emailProfessionnel,
			@Size(max = 40, message = "Un email ne peut pas depasser 40 caractères!") String emailPersonnel,
			@Size(max = 40, message = "Un nom et prenom en arabe ne peut pas depasser 40 caractères!") String nomPrenomArabe,
			@Size(max = 100, message = "Une adresse ne peut pas depasser 100 caractères!") String adresse,
			String motDePasse) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		CIN = cIN;
		this.dateNaissance = dateNaissance;
		this.sexe = sexe;
		this.telephone = telephone;
		this.emailProfessionnel = emailProfessionnel;
		this.emailPersonnel = emailPersonnel;
		this.nomPrenomArabe = nomPrenomArabe;
		this.adresse = adresse;
		this.motDePasse = motDePasse;
	}

	protected Personne() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getCIN() {
		return CIN;
	}

	public void setCIN(String cIN) {
		CIN = cIN;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public Sexe getSexe() {
		return sexe;
	}

	public void setSexe(Sexe sexe) {
		this.sexe = sexe;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmailProfessionnel() {
		return emailProfessionnel;
	}

	public void setEmailProfessionnel(String emailProfessionnel) {
		this.emailProfessionnel = emailProfessionnel;
	}

	public String getEmailPersonnel() {
		return emailPersonnel;
	}

	public void setEmailPersonnel(String emailPersonnel) {
		this.emailPersonnel = emailPersonnel;
	}

	public String getNomPrenomArabe() {
		return nomPrenomArabe;
	}

	public void setNomPrenomArabe(String nomPrenomArabe) {
		this.nomPrenomArabe = nomPrenomArabe;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((CIN == null) ? 0 : CIN.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Personne other = (Personne) obj;
		if (CIN == null) {
			if (other.CIN != null)
				return false;
		} else if (!CIN.equals(other.CIN))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
