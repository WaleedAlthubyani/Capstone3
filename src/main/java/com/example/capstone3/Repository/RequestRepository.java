package com.example.capstone3.Repository;

import com.example.capstone3.Model.Contributor;
import com.example.capstone3.Model.Request;
import com.example.capstone3.Model.Researcher;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request,Integer> {

    Request findRequestById(Integer id);

    List<Request> findRequestsByContributor(Contributor contributor);

    Request findRequestByContributorAndDecision (Contributor contributor ,String Decision );



    List<Request> findRequestsByDecisionAndContributor(@Pattern(regexp = "^(?i)(pending|accepted|rejected)$") String decision, Contributor contributor);

    List<Request> findRequestsByContributorAndResearcherAndDecision(Contributor contributor, Researcher researcher, String decision);

    @Query("select r from Request r where r.researcher.id=?1")
    List<Request> findByResearcherId (Integer researcher_id);
}
