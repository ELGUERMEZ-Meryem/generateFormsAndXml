package generateFormsXml.api.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class FieldFormlyModel {
    private String key;
    private String type;
    private Object defaultValue;
    private List<String> wrappers;
    private TemplateOption templateOptions;
    private List<FieldFormlyModel> fieldGroup;
}

