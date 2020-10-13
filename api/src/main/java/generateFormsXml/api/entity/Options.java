package generateFormsXml.api.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Options extends AbstractEntity {
    private String name;
    private String value;

    @ManyToOne(optional = false)
    private Field field;
}
