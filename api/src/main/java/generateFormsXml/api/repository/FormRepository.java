package generateFormsXml.api.repository;

import generateFormsXml.api.entity.Country;
import generateFormsXml.api.entity.Form;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormRepository extends JpaRepository<Form, Long> {
    Form findByUser_EmailAndTemplate_Country(String email, Country country);
}
