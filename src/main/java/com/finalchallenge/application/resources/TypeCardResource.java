package com.finalchallenge.application.resources;

import com.finalchallenge.application.resources.dto.card.TypeCardResponseDTO;
import com.finalchallenge.application.resources.dto.card.TypeCardDTO;
import com.finalchallenge.application.services.TypeCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/type-card")
public class TypeCardResource {

    @Autowired
    TypeCardService service;

    @GetMapping
    public ResponseEntity<List<TypeCardResponseDTO>> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TypeCardResponseDTO> geById(@PathVariable Integer id) {
        return service.getById(id);
    }

    @PostMapping
    public ResponseEntity<TypeCardResponseDTO> save(@RequestBody @Valid TypeCardDTO dto, UriComponentsBuilder uriBuilder) {
        return service.save(dto, uriBuilder);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Integer id) {
        return service.deleteById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TypeCardResponseDTO> update(@PathVariable Integer id, @RequestBody @Valid TypeCardDTO dto) {
        return service.update(id, dto);
    }

}
