package generateFormsXml.api.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class FieldFormlyModel {
    private String key;
    private String type;
    private List<Object> defaultValue;
    private List<String> wrappers;
    private TemplateOption templateOptions;
    private FieldArray fieldArray;
    private List<FieldFormlyModel> fieldGroup;
}

