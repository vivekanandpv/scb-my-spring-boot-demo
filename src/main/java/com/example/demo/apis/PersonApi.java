package com.example.demo.apis;

import com.example.demo.models.Person;
import com.example.demo.services.PersonService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<Person>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Person> getById(@PathVariable int id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<Person> create(@RequestBody Person person) {
        return ResponseEntity.ok(service.create(person));
    }

    @PutMapping("{id}")
    public ResponseEntity<Person> updatePut(@PathVariable int id, @RequestBody Person person) {
        return ResponseEntity.ok(service.update(id, person));
    }

    @PatchMapping("{id}")
    public ResponseEntity<Person> updatePatch(@PathVariable int id, @RequestBody Person person) {
        return ResponseEntity.ok(service.update(id, person));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteById(@PathVariable int id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
