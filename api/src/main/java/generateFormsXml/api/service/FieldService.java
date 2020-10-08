package generateFormsXml.api.service;

import generateFormsXml.api.entity.Field;
import generateFormsXml.api.entity.Form;
import generateFormsXml.api.entity.Template;
import generateFormsXml.api.entity.User;
import generateFormsXml.api.exception.ElementNotFoundException;
import generateFormsXml.api.model.FieldFormlyModel;
import generateFormsXml.api.model.Option;
import generateFormsXml.api.model.TemplateOption;
import generateFormsXml.api.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FieldService implements IField {

    private final FieldRepository fieldRepository;
    private final UserRepository userRepository;
    private final FormRepository formRepository;
    private final TemplateRepository templateRepository;
    private final CountryRepository countryRepository;

    public FieldService(FieldRepository fieldRepository, UserRepository userRepository, FormRepository formRepository, TemplateRepository templateRepository, CountryRepository countryRepository) {
        this.fieldRepository = fieldRepository;
        this.userRepository = userRepository;
        this.formRepository = formRepository;
        this.templateRepository = templateRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public List<FieldFormlyModel> getAllFieldsByCountryAlpha2Code(String email) throws ElementNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new ElementNotFoundException("User not found"));
        Form form = formRepository.findByUser_EmailAndTemplate_Country(user.getEmail(), user.getCountry());
        return fieldRepository.findByTemplate_Country_OrderByFieldOrderAsc(user.getCountry()).stream().map(field -> convert(field, form)).collect(Collectors.toList());
    }

    @Override
    public void saveXmlValues(Object object, String email) throws ElementNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new ElementNotFoundException("User not found"));
        Form form = formRepository.findByUser_EmailAndTemplate_Country(user.getEmail(), user.getCountry());
        if (form != null) {
            form.setValue((Map<String, Object>) object);
            formRepository.save(form);
        } else {
            Template template = templateRepository.findByCountry(user.getCountry()).orElseThrow(() -> new ElementNotFoundException("No template found for this country"));
            formRepository.save(Form.builder().template(template).user(user).value((Map<String, Object>) object).build());
        }

    }

    private FieldFormlyModel convert(Field field, Form form) {
        FieldFormlyModel f = FieldFormlyModel.builder()
                .key(field.getNodeName())
                .type(field.getFieldType())
                .templateOptions(TemplateOption.builder().label(field.getLabel()).disabled(!field.getIsEditable()).placeholder(field.getLabel()).required(field.getIsMandatory()).build())
                .build();

        if (form != null)
            f.setDefaultValue((String) form.getValue().get(field.getNodeName()));

        if (field.getFieldType().equals("input")) {
            f.getTemplateOptions().setMaxLength(field.getMaxLength());
            f.getTemplateOptions().setMinLength(field.getMinLength());
        }

        if (field.getFieldType().equals("select"))
            f.getTemplateOptions().setOptions(countryRepository.findAll().stream().map(country -> Option.builder().value(country.getAlpha2code()).label(country.getName()).build()).collect(Collectors.toList()));

        return f;
    }
}
