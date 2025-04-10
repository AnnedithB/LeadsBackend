package com.example.leads.repository;
import java.util.Optional;
import com.example.leads.model.Lead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
//helps with ready made functions by inheriting from jparepo
@Repository
public interface LeadRepository extends JpaRepository<Lead, UUID> {
    Optional<Lead> findByEmail(String email);
}

