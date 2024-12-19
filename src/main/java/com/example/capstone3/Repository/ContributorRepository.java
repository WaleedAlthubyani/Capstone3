package com.example.capstone3.Repository;

import com.example.capstone3.Model.Contributor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
//Waleed
@Repository
public interface ContributorRepository extends JpaRepository<Contributor,Integer> {

    Contributor findContributorById(Integer id);

    boolean existsByEmail(String email);

    boolean existsByPhoneNumber(String phoneNumber);
}
