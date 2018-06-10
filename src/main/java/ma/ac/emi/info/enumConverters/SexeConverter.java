package ma.ac.emi.info.enumConverters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import ma.ac.emi.info.enums.Sexe;

@Converter(autoApply = true)
public class SexeConverter implements AttributeConverter<Sexe, String> {
 
    public SexeConverter() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
    public String convertToDatabaseColumn(Sexe sexe) {
        return sexe.getID();
    }
 
    @Override
    public Sexe convertToEntityAttribute(String databaseValue) {
        return Sexe.getType(databaseValue);
    }
 
}