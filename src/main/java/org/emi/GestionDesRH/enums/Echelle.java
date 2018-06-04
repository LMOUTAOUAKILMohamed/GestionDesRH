package org.emi.GestionDesRH.enums;

public enum Echelle {
	
	ECHELLE_1(1), ECHELLE_2(2), ECHELLE_3(3), ECHELLE_4(4), ECHELLE_5(5),
	ECHELLE_6(6), ECHELLE_7(7), ECHELLE_8(8), ECHELLE_9(9), ECHELLE_10(10), ECHELLE_11(11), HORS_ECHELLE(0);

	private final int id;
	 
	Echelle(int id) {
        this.id = id;
    }
 
    public static Echelle getType(Integer id) {
        if (id == null) {
            return null;
        }
 
        for (Echelle echelle : Echelle.values()) {
            if (id.equals(echelle.getID())) {
                return echelle;
            }
        }
 
        return null;
    }
 
    public int getID() {
        return id;
    }
	
}
