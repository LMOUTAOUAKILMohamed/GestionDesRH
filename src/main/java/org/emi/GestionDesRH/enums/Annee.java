package org.emi.GestionDesRH.enums;

public enum Annee {
	
	PREMIERE_ANNEE(1), DEUXIEM_ANNEE(2), TROISIEME_ANNEE(3);
	
	private final int id;
	 
	Annee(int id) {
        this.id = id;
    }
 
    public static Annee getType(Integer id) {
        if (id == null) {
            return null;
        }
 
        for (Annee annee : Annee.values()) {
            if (id.equals(annee.getID())) {
                return annee;
            }
        }
 
        return null;
    }
 
    public int getID() {
        return id;
    }

}
