package ma.ac.emi.info.enumConverters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import ma.ac.emi.info.enums.Fonction;

@Converter(autoApply = true)
public class FonctionConverter implements AttributeConverter<Fonction, String> {
 
    @Override
    public String convertToDatabaseColumn(Fonction fonction) {
        return fonction.getID();
    }
 
    @Override
    public Fonction convertToEntityAttribute(String databaseValue) {
        return Fonction.getType(databaseValue);
    }
 
}
