package generateFormsXml.api.repository;

import generateFormsXml.api.entity.Country;
import generateFormsXml.api.entity.Template;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TemplateRepository extends JpaRepository<Template, Long> {
    Optional<Template> findByCountry(Country country);
}
