package generateFormsXml.api.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FieldFormlyModel {
    private String key;
    private String type;
    private Object defaultValue;
    private TemplateOption templateOptions;
}

