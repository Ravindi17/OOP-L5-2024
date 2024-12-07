package com.TicketingSystem.repository;

import com.TicketingSystem.model.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface VendorRepository extends JpaRepository<Vendor, Long> {
}
