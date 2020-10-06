package generateFormsXml.api.service;

import generateFormsXml.api.entity.Field;
import generateFormsXml.api.entity.Template;
import generateFormsXml.api.entity.User;
import generateFormsXml.api.exception.ElementNotFoundException;
import generateFormsXml.api.repository.FieldRepository;
import generateFormsXml.api.repository.FormRepository;
import generateFormsXml.api.repository.TemplateRepository;
import generateFormsXml.api.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FieldService implements IField {

    private final FieldRepository fieldRepository;
    private final UserRepository userRepository;
    private final FormRepository formRepository;
    private final TemplateRepository templateRepository;

    public FieldService(FieldRepository fieldRepository, UserRepository userRepository, FormRepository formRepository, TemplateRepository templateRepository) {
        this.fieldRepository = fieldRepository;
        this.userRepository = userRepository;
        this.formRepository = formRepository;
        this.templateRepository = templateRepository;
    }

    @Override
    public List<Field> getAllFieldsByCountryAlpha2Code(String email) throws ElementNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new ElementNotFoundException("User not found"));
        return fieldRepository.findByTemplate_Country(user.getCountry());
    }

    @Override
    public void saveXmlValues(Object object, String email) {

    }
}
