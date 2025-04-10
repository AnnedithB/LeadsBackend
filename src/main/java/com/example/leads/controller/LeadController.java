package com.example.leads.controller;

import com.example.leads.model.Lead;
import com.example.leads.repository.LeadRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.UUID;

//allows cors requests
@CrossOrigin(origins = "https://leads-management-system-tau.vercel.app")
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
    public ResponseEntity<?> createLead(@RequestBody Lead lead) {
        // Check for duplicate email
        if (leadRepository.findByEmail(lead.getEmail()).isPresent()) {
            // Email already exists
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("Email already exists");
        }

        // Save the lead if no duplicate email is found
        Lead savedLead = leadRepository.save(lead);
        return new ResponseEntity<>(savedLead, HttpStatus.CREATED);
    }


    // DELETE /leads/{id} - Delete a lead by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLead(@PathVariable UUID id) {
        if (!leadRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        leadRepository.deleteById(id);
        return ResponseEntity.ok("Lead deleted");
    }


}
