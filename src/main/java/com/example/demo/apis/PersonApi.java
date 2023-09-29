package com.example.demo.apis;

import com.example.demo.models.Person;
import com.example.demo.services.PersonService;
import com.example.demo.viewmodels.PersonCreateViewModel;
import com.example.demo.viewmodels.PersonUpdateViewModel;
import com.example.demo.viewmodels.PersonViewModel;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/persons")
@Api(value = "persons", tags = {"Person API"})
public class PersonApi {
    private final PersonService service;

    public PersonApi(PersonService service) {
        this.service = service;
    }


    @GetMapping
    public ResponseEntity<List<PersonViewModel>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<PersonViewModel> getById(@PathVariable int id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<PersonViewModel> create(@Valid @RequestBody PersonCreateViewModel person) {
        return ResponseEntity.ok(service.create(person));
    }

    @PutMapping("{id}")
    public ResponseEntity<PersonViewModel> updatePut(@Valid @PathVariable int id, @RequestBody PersonUpdateViewModel person) {
        return ResponseEntity.ok(service.update(id, person));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteById(@PathVariable int id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
