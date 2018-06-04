package org.emi.GestionDesRH.enumConverters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.emi.GestionDesRH.enums.Echelle;

@Converter(autoApply = true)
public class EchelleConverter implements AttributeConverter<Echelle, Integer> {
 
    @Override
    public Integer convertToDatabaseColumn(Echelle echelle) {
        return echelle.getID();
    }
 
    @Override
    public Echelle convertToEntityAttribute(Integer databaseValue) {
        return Echelle.getType(databaseValue);
    }
 
}
