package generateFormsXml.api.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Option {
    private String label;
    private Object value;
}
