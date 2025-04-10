package com.example.leads.controller;

import com.example.leads.model.Lead;
import com.example.leads.repository.LeadRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/leads")
public class LeadController {

    private final LeadRepository leadRepository;

    public LeadController(LeadRepository leadRepository) {
        this.leadRepository = leadRepository;
    }

    // GET /leads - Retrieve all leads
    @GetMapping
    public ResponseEntity<List<Lead>> getAllLeads() {
        List<Lead> leads = leadRepository.findAll();
        return ResponseEntity.ok(leads);
    }

    // POST /leads - Create a new lead
    @PostMapping
    public ResponseEntity<Lead> createLead(@RequestBody Lead lead) {
        // Basic validation can be extended (e.g., checking duplicate emails, etc.)
        Lead savedLead = leadRepository.save(lead);
        return new ResponseEntity<>(savedLead, HttpStatus.CREATED);
    }

    // DELETE /leads/{id} - Delete a lead by ID (optional)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLead(@PathVariable UUID id) {
        if (!leadRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        leadRepository.deleteById(id);
        return ResponseEntity.ok("Lead deleted");
    }

    // Additional error handling can be added with @ExceptionHandler methods
}
