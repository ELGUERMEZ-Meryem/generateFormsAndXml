package generateFormsXml.api.repository;

import generateFormsXml.api.entity.Country;
import generateFormsXml.api.entity.Field;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FieldRepository extends JpaRepository<Field, Long> {
    List<Field> findByTemplate_Country_OrderByFieldOrderAsc(Country country);
    List<Field> findByTemplate_CountryAndParentField_OrderByFieldOrderAsc(Country country, Field fieldParent);
    List<Field> findByTemplate_CountryAndAttributeField_OrderByFieldOrderAsc(Country country, Field attributeField);
}
