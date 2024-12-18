package com.example.capstone3.Repository;

import com.example.capstone3.Model.Organization;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization,Integer> {
    Organization findOrganizationById (Integer id);

    @Query("select o from Organization o where o.status='approved'")
    List<Organization> findAllApprovedOrganizations();

    @Query("select o from Organization o where o.status='pending'")
    List<Organization> findAllPendingOrganizations();
    
    List<Organization> findByStatusIsNot (String status);
}
