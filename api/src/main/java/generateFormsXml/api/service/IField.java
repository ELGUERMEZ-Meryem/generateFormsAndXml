package generateFormsXml.api.service;

import generateFormsXml.api.exception.ElementNotFoundException;
import generateFormsXml.api.model.FieldFormlyModel;

import java.util.List;

public interface IField {
    List<FieldFormlyModel> getAllFieldsByCountryAlpha2Code(String email) throws ElementNotFoundException;

    void saveXmlValues(Object object, String email) throws ElementNotFoundException;
}
