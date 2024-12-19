package com.example.capstone3.Repository;

import com.example.capstone3.Model.Researcher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
//Waleed
@Repository
public interface ResearcherRepository extends JpaRepository<Researcher,Integer> {

    Researcher findResearcherById(Integer id);

    @Query("select r from Researcher r where r.status='approved'")
    List<Researcher> findAllApprovedResearcher();

    @Query("select r from Researcher r where r.status='pending'")
    List<Researcher> findAllPendingResearcher();

    boolean existsByEmail(String email);

    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByLicenseURL(String licenceURL);
}
