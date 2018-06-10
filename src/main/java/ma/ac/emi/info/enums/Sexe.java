package ma.ac.emi.info.enums;

public enum Sexe {
	
	M("M"), F("F");
	
	private String id;

	private Sexe(String id) {
		this.id = id;
	}
 
    public static Sexe getType(String id) {
        if (id == null) {
            return null;
        }
 
        for (Sexe sexe : Sexe.values()) {
            if (id.equals(sexe.getID())) {
                return sexe;
            }
        }
 
        return null;
    }
 
    public String getID() {
        return id;
    }
}
