package generateFormsXml.api.repository;

import generateFormsXml.api.entity.Form;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormRepository extends JpaRepository<Form, Long> {
}
