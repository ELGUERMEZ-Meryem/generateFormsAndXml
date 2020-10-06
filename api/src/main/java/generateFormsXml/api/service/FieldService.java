package generateFormsXml.api.service;

import generateFormsXml.api.entity.Field;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FieldService implements IField {
    @Override
    public List<Field> getAllFieldsByCountryAlpha2Code(String email) {
        return null;
    }

    @Override
    public void saveXmlValues(Object object, String email) {

    }
}
