package com.example.capstone3.Repository;

import com.example.capstone3.Model.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CertificateRepository extends JpaRepository<Certificate, Integer> {
Certificate findCertificateById (Integer id);
List<Certificate> findByExpirationDateBefore (LocalDate expirationDate);
}
