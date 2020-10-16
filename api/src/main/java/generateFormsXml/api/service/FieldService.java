package generateFormsXml.api.service;

import generateFormsXml.api.entity.*;
import generateFormsXml.api.exception.ElementNotFoundException;
import generateFormsXml.api.model.FieldArray;
import generateFormsXml.api.model.FieldFormlyModel;
import generateFormsXml.api.model.Option;
import generateFormsXml.api.model.TemplateOption;
import generateFormsXml.api.repository.*;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class FieldService implements IField {

    private final FieldRepository fieldRepository;
    private final UserRepository userRepository;
    private final FormRepository formRepository;
    private final TemplateRepository templateRepository;
    private final OptionsRepository optionsRepository;

    public FieldService(FieldRepository fieldRepository, UserRepository userRepository, FormRepository formRepository, TemplateRepository templateRepository, OptionsRepository optionsRepository) {
        this.fieldRepository = fieldRepository;
        this.userRepository = userRepository;
        this.formRepository = formRepository;
        this.templateRepository = templateRepository;
        this.optionsRepository = optionsRepository;
    }

    @Override
    public List<FieldFormlyModel> getAllFieldsByCountryAlpha2Code(String email) throws ElementNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new ElementNotFoundException("User not found"));
        Form form = formRepository.findByUser_EmailAndTemplate_Country(user.getEmail(), user.getCountry());
        return fieldRepository.findByTemplate_Country_OrderByFieldOrderAsc(user.getCountry()).stream().filter(field -> field.getParentField() == null && field.getAttributeField() == null).map(field -> convert(field, form, user.getCountry())).collect(Collectors.toList());
    }

    @Override
    public void saveXmlValues(Object object, String email) throws ElementNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new ElementNotFoundException("User not found"));
        Form form = formRepository.findByUser_EmailAndTemplate_Country(user.getEmail(), user.getCountry());
        System.out.println("eee "+object);
        if (form != null) {
            form.setValue((Map<String, Object>) object);
            formRepository.save(form);
        } else {
            Template template = templateRepository.findByCountry(user.getCountry()).orElseThrow(() -> new ElementNotFoundException("No template found for this country"));
            formRepository.save(Form.builder().template(template).user(user).value((Map<String, Object>) object).build());
        }

    }

    private FieldFormlyModel convert(Field field, Form form, Country c) {
        FieldFormlyModel f = FieldFormlyModel.builder()
                .key(field.getNodeName())
                .templateOptions(TemplateOption.builder().label(field.getLabel()).maxItems(field.getMaxOccurs()).minItems(field.getMinOccurs()).disabled(!field.getIsEditable()).placeholder(field.getLabel()).required(field.getIsMandatory()).build())
                .build();

        if (field.getIsComplexType() == null || !field.getIsComplexType()) {
            f.setType("repeat");
            f.setFieldArray(FieldArray.builder().type(field.getFieldType()).build());
            if (form != null)
                f.setDefaultValue(Arrays.asList(form.getValue().get(field.getNodeName())));
        }

        if (field.getIsComplexType() != null && field.getIsComplexType()) {
            f.setWrappers(Arrays.asList("panel"));
            f.setFieldGroup(fieldRepository.findByTemplate_CountryAndParentField_OrderByFieldOrderAsc(c, field).stream().map(field1 -> convert(field1, form, c)).map(fieldFormlyModel -> {
                if (form != null && form.getValue().get(field.getNodeName()) != null)
                    fieldFormlyModel.setDefaultValue(Arrays.asList(((LinkedHashMap) form.getValue().get(field.getNodeName())).get(fieldFormlyModel.getKey())));
                return fieldFormlyModel;
            }).collect(Collectors.toList()));

            f.setFieldGroup(Stream.concat(f.getFieldGroup().stream(), fieldRepository.findByTemplate_CountryAndAttributeField_OrderByFieldOrderAsc(c, field).stream().map(field1 -> convert(field1, form, c)).map(fieldFormlyModel -> {
                fieldFormlyModel.setKey("@"+fieldFormlyModel.getKey());
                if (form != null && form.getValue().get(field.getNodeName()) != null)
                    fieldFormlyModel.setDefaultValue(Arrays.asList(((LinkedHashMap) form.getValue().get(field.getNodeName())).get(fieldFormlyModel.getKey())));
                return fieldFormlyModel;
            })).collect(Collectors.toList()));
        }

        if (field.getFieldType().equals("input")) {
            f.getTemplateOptions().setMaxLength(field.getMaxLength());
            f.getTemplateOptions().setMinLength(field.getMinLength());
        }

        if (field.getFieldType().equals("select"))
            f.getTemplateOptions().setOptions(optionsRepository.findByField_NodeName(field.getNodeName()).stream().map(op -> Option.builder().value(op.getValue()).label(op.getName()).build()).collect(Collectors.toList()));

        if (field.getFieldType().equals("radio"))
            f.getTemplateOptions().setOptions(optionsRepository.findByField_NodeName(field.getNodeName()).stream().map(op -> Option.builder().value(op.getValue()).label(op.getName()).build()).collect(Collectors.toList()));
        return f;
    }
}
