package generateFormsXml.api.service;

import generateFormsXml.api.entity.Field;
import generateFormsXml.api.exception.ElementNotFoundException;

import java.util.List;

public interface IField {
    List<Field> getAllFieldsByCountryAlpha2Code(String email) throws ElementNotFoundException;

    void saveXmlValues(Object object, String email);
}
