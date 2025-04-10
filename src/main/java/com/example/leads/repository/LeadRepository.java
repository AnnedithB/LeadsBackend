package com.example.leads.repository;

import com.example.leads.model.Lead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LeadRepository extends JpaRepository<Lead, UUID> {
    // Additional query methods (if needed) can be defined here.
}

