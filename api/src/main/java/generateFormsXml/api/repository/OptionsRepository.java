package generateFormsXml.api.repository;

import generateFormsXml.api.entity.Options;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OptionsRepository extends JpaRepository<Options, Long> {
    List<Options> findByField_NodeName(String nodeName);
}
