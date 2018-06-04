package org.emi.GestionDesRH.enumConverters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.emi.GestionDesRH.enums.Annee;

@Converter(autoApply = true)
public class AnneeConverter implements AttributeConverter<Annee, Integer> {
 
    @Override
    public Integer convertToDatabaseColumn(Annee annee) {
        return annee.getID();
    }
 
    @Override
    public Annee convertToEntityAttribute(Integer databaseValue) {
        return Annee.getType(databaseValue);
    }
 
}