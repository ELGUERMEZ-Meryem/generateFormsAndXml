package generateFormsXml.api.controller;

import generateFormsXml.api.service.FieldService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class FieldController {

    private final FieldService fieldService;

    public FieldController(FieldService fieldService) {
        this.fieldService = fieldService;
    }

    @GetMapping(value = "/fields/{email}")
    public ResponseEntity<?> getAllFields(@PathVariable String email) {
        System.out.println("111111 ");
        return ResponseEntity.status(HttpStatus.OK).body(fieldService.getAllFieldsByCountryAlpha2Code(email));
    }

    @PostMapping("/values/{email}")
    public ResponseEntity<?> saveValues(@RequestBody Object object, @PathVariable String email) {
        System.out.println("hddhhdhh ");
        fieldService.saveXmlValues(object, email);
        return ResponseEntity.status(HttpStatus.OK).body(true);
    }
}
