package generateFormsXml.api.service;

import generateFormsXml.api.entity.Field;

import java.util.List;

public interface IField {
    List<Field> getAllFieldsByCountryAlpha2Code(String email);

    void saveXmlValues(Object object, String email);
}
