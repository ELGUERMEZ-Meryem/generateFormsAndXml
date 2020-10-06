package generateFormsXml.api.controller;

import generateFormsXml.api.exception.ElementNotFoundException;
import generateFormsXml.api.service.FieldService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FieldController {

    private final FieldService fieldService;

    public FieldController(FieldService fieldService) {
        this.fieldService = fieldService;
    }

    @GetMapping(value = "/fields/{email}")
    public ResponseEntity<?> getAllFields(@PathVariable String email) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(fieldService.getAllFieldsByCountryAlpha2Code(email));
        } catch (ElementNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(true);
        }
    }

    @PostMapping("/values/{email}")
    public ResponseEntity<?> saveValues(@RequestBody Object object, @PathVariable String email) {
        fieldService.saveXmlValues(object, email);
        return ResponseEntity.status(HttpStatus.OK).body(true);
    }
}
