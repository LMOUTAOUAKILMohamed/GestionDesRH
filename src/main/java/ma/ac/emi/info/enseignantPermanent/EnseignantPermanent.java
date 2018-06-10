package ma.ac.emi.info.enseignantPermanent;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import ma.ac.emi.info.enseignant.Enseignant;

@Entity
@DiscriminatorValue("EP")
public class EnseignantPermanent extends Enseignant{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	

}
