package org.emi.GestionDesRH.enums;

public enum Grade {
	
	PA("PA"), PH("PH"), PES("PES"), INGENIEUR("ING");
	
	private final String id;
	 
	Grade(String id) {
        this.id = id;
    }
 
    public static Grade getType(String id) {
        if (id == null) {
            return null;
        }
 
        for (Grade grade : Grade.values()) {
            if (id.equals(grade.getID())) {
                return grade;
            }
        }
 
        return null;
    }
 
    public String getID() {
        return id;
    }

}
