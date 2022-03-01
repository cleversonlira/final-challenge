package com.finalchallenge.application.resources;

import com.finalchallenge.application.resources.dto.AccountDTO;
import com.finalchallenge.application.resources.dto.AccountResponseDTO;
import com.finalchallenge.application.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountResource {

    @Autowired
    AccountService service;

    @GetMapping
    public ResponseEntity<List<AccountResponseDTO>> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountResponseDTO> geById(@PathVariable Integer id) {
        return service.getById(id);
    }

    @PostMapping
    public ResponseEntity<AccountResponseDTO> save(@RequestBody @Valid AccountDTO dto, UriComponentsBuilder uriBuilder) {
        return service.save(dto, uriBuilder);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Integer id) {
        return service.deleteById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountResponseDTO> update(@PathVariable Integer id, @RequestBody @Valid AccountDTO dto) {
        return service.update(id, dto);
    }

}
