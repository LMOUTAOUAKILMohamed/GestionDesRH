package ma.ac.emi.info.etudiant;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import ma.ac.emi.info.enums.Sexe;
import ma.ac.emi.info.personne.Personne;

@Entity
@Table(name = "ARCHIVE_ETUDIANTS")
public class EtudiantArchive extends Personne{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotNull
	@Column(name = "MATRICULE")
	private Long matricule;
	
	@Column( name="CNE")
	protected Long CNE;
	
	//@NotNull
	@Column(name = "DATE_INSCRIPTION")
	private int dateInscription;
	
	@Column(name = "PHOTO")
	private boolean photo = false;
	
	@Column(name = "CV")
	private boolean cv = false;
	
	@Column(name = "PROMOTION")
	private int promotion;
	
	
	public EtudiantArchive() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public EtudiantArchive(Long id,
			@NotNull @Size(max = 30, message = "Un nom ne peut pas depasser 30 caractères!") String nom,
			@NotNull @Size(max = 20, message = "Un prenom ne peut pas depasser 20 caractères!") String prenom,
			@NotNull @Size(max = 8, message = "Un CIN ne peut pas depasser 8 caractères!") String cIN,
			@NotNull Date dateNaissance, @NotNull Sexe sexe,
			@NotNull @Size(max = 15, message = "Un numéro de téléphone ne peut pas depasser 15 caractères!") String telephone,
			@Size(max = 40, message = "Un email ne peut pas depasser 40 caractères!") String emailProfessionnel,
			@Size(max = 40, message = "Un email ne peut pas depasser 40 caractères!") String emailPersonnel,
			@Size(max = 40, message = "Un nom et prenom en arabe ne peut pas depasser 40 caractères!") String nomPrenomArabe,
			@Size(max = 100, message = "Une adresse ne peut pas depasser 100 caractères!") String adresse,
			String motDePasse, @NotNull Long matricule, Long cNE, int dateInscription, boolean photo, boolean cv,
			int promotion) {
		super(id, nom, prenom, cIN, dateNaissance, sexe, telephone, emailProfessionnel, emailPersonnel, nomPrenomArabe,
				adresse, motDePasse);
		this.matricule = matricule;
		CNE = cNE;
		this.dateInscription = dateInscription;
		this.photo = photo;
		this.cv = cv;
		this.promotion = promotion;
	}

	public EtudiantArchive(Etudiant etudiant) {
		super(null, etudiant.getNom(), etudiant.getPrenom(), etudiant.getCIN(), etudiant.getDateNaissance(), etudiant.getSexe(), etudiant.getTelephone(), etudiant.getEmailProfessionnel(), etudiant.getEmailPersonnel(), etudiant.getNomPrenomArabe(), etudiant.getAdresse(), null);
		matricule = etudiant.getMatricule();
		CNE = etudiant.getCNE();
		dateInscription = etudiant.getDateInscription();
		photo = etudiant.isPhoto();
		promotion = etudiant.getPromotion();
		cv = etudiant.isCv();
	}

	public Long getMatricule() {
		return matricule;
	}

	public void setMatricule(Long matricule) {
		this.matricule = matricule;
	}

	public Long getCNE() {
		return CNE;
	}

	public void setCNE(Long cNE) {
		CNE = cNE;
	}

	public int getDateInscription() {
		return dateInscription;
	}

	public void setDateInscription(int dateInscription) {
		this.dateInscription = dateInscription;
	}

	public boolean isPhoto() {
		return photo;
	}

	public void setPhoto(boolean photo) {
		this.photo = photo;
	}

	public int getPromotion() {
		return promotion;
	}

	public void setPromotion(int promotion) {
		this.promotion = promotion;
	}

	public boolean isCv() {
		return cv;
	}

	public void setCv(boolean cv) {
		this.cv = cv;
	}
	
	

}
