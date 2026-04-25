package com.example.homemortgages.controller;

import com.example.homemortgages.domain.MortgageApplication;
import com.example.homemortgages.service.MortgageApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/mortgages")
@RequiredArgsConstructor
public class MortgageApplicationController {

    private final MortgageApplicationService service;

    @PostMapping
    public ResponseEntity<MortgageApplication> createApplication(@Valid @RequestBody MortgageApplication application) {
        MortgageApplication created = service.createApplication(application);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<MortgageApplication>> getAllApplications() {
        return ResponseEntity.ok(service.getAllApplications());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MortgageApplication> getApplicationById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getApplicationById(id));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<MortgageApplication> updateApplicationStatus(@PathVariable Long id, @RequestParam String status) {
        return ResponseEntity.ok(service.updateApplicationStatus(id, status));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteApplication(@PathVariable Long id) {
        service.deleteApplication(id);
        return ResponseEntity.noContent().build();
    }
}
