package ma.ac.emi.info.enums;

public enum Fonction {
	
	ADMINISTRATEUR("A"), CHEF_DE_DEPARTEMENT("C"), SECRETAIRE("S"), TECHNICIEN("T");
	
	private final String id;
	 
	Fonction(String id) {
        this.id = id;
    }
 
    public static Fonction getType(String id) {
        if (id == null) {
            return null;
        }
 
        for (Fonction fonction : Fonction.values()) {
            if (id.equals(fonction.getID())) {
                return fonction;
            }
        }
 
        return null;
    }
 
    public String getID() {
        return id;
    }

}
