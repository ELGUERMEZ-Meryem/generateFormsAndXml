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
public class Template extends AbstractEntity {
    @Column(nullable = false)
    private String name;

    @ManyToOne(optional = false)
    private Country country;
}
