package generateFormsXml.api.repository;

import generateFormsXml.api.entity.Field;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FieldRepository extends JpaRepository<Field, Long> {
}
