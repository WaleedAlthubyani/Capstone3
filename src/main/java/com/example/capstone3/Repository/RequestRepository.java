package com.example.capstone3.Repository;

import com.example.capstone3.Model.Artifact;
import com.example.capstone3.Model.Contributor;
import com.example.capstone3.Model.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request,Integer> {

    Request findRequestById(Integer id);

    List<Request> findRequestsByContributor(Contributor contributor);

    Request findRequestByContributorAndDecision (Contributor contributor ,String Decision );

}
