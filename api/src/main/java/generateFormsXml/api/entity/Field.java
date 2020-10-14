package generateFormsXml.api.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Field extends AbstractEntity {

    @Column(nullable = false)
    private String nodeName;

    private String inputName;

    private String label;

    private String fieldType;

    @Column(nullable = false)
    private Integer minOccurs;

    @Column(nullable = false)
    private Integer maxOccurs;

    private Integer minLength;

    private Integer maxLength;

    private Boolean isMandatory;

    private Boolean isEditable;

    private Integer fieldOrder;

    @ManyToOne(optional = false)
    private Template template;

    private Boolean isComplexType;

    @ManyToOne
    private Field parentField;

    @ManyToOne
    private Field attributeField;
}
