package generateFormsXml.api.entity;

import generateFormsXml.api.utils.HashMapConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Map;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Form extends AbstractEntity {

    @ManyToOne(optional = false)
    private User user;

    @ManyToOne(optional = false)
    private Template template;

    @Lob
    @Column(columnDefinition = "json")
    @Convert(converter = HashMapConverter.class)
    private Map<String, Object> value;}
