package ma.ac.emi.info.enumConverters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import ma.ac.emi.info.enums.Grade;

@Converter(autoApply = true)
public class GradeConverter implements AttributeConverter<Grade, String> {
 
    @Override
    public String convertToDatabaseColumn(Grade grade) {
        return grade.getID();
    }
 
    @Override
    public Grade convertToEntityAttribute(String databaseValue) {
        return Grade.getType(databaseValue);
    }
 
}