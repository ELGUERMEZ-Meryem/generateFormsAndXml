package generateFormsXml.api.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
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

}
