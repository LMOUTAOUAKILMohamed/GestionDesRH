package org.emi.GestionDesRH.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("EP")
public class EnseignantPermanent extends Enseignant{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	

}
