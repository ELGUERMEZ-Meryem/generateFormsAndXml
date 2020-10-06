package generateFormsXml.api.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class TemplateOption {
    private String label;
    private String type;
    private String placeholder;
    private Boolean required;
    private Boolean disabled;
    private List<Option> options;
}
