package com.finalchallenge.application.resources;

import com.finalchallenge.application.resources.dto.CardDto;
import com.finalchallenge.application.resources.dto.CardResponseDTO;
import com.finalchallenge.application.services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/card")
public class CardResource {

    @Autowired
    CardService service;

    @GetMapping
    public ResponseEntity<List<CardResponseDTO>> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CardResponseDTO> geById(@PathVariable Integer id) {
        return service.getById(id);
    }

    @PostMapping
    public ResponseEntity<CardResponseDTO> save(@RequestBody @Valid CardDto dto, UriComponentsBuilder uriBuilder) {
        return service.save(dto, uriBuilder);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Integer id) {
        return service.deleteById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CardResponseDTO> update(@PathVariable Integer id, @RequestBody @Valid CardDto dto) {
        return service.update(id, dto);
    }

}
