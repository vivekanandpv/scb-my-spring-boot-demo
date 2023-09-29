package com.example.demo.apis;

import com.example.demo.viewmodels.CarCreateViewModel;
import com.example.demo.viewmodels.CarUpdateViewModel;
import com.example.demo.viewmodels.CarViewModel;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/cars")
@Api(value = "cars", tags = {"Car API"})
public class CarApi {
    @GetMapping
    public ResponseEntity<List<CarViewModel>> getAll() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("{id}")
    public ResponseEntity<CarViewModel> getById(@PathVariable int id) {
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<CarViewModel> create(@RequestBody CarCreateViewModel viewModel) {
        return ResponseEntity.ok().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<CarViewModel> update(@PathVariable int id, @RequestBody CarUpdateViewModel viewModel) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteById(@PathVariable int id) {
        return ResponseEntity.ok().build();
    }
}
