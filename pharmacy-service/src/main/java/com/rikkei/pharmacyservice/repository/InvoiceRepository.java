package com.rikkei.pharmacyservice.repository;
import com.rikkei.pharmacyservice.entity.Invoice; import org.springframework.data.jpa.repository.JpaRepository;
public interface InvoiceRepository extends JpaRepository<Invoice,Long> {}
