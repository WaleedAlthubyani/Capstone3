package com.example.capstone3.Repository;

import com.example.capstone3.Model.Artifact;
import com.example.capstone3.Model.Researcher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArtifactRepository extends JpaRepository<Artifact, Integer> {

    Artifact findArtifactsById(Integer id);

    @Query("select a from Artifact a where a.status='approved'")
    List<Artifact> findAllApprovedArtifacts();

    @Query("select a from Artifact a where a.status='pending'")
    List<Artifact> findAllPendingArtifacts();

    List<Artifact> findArtifactByType (String type);
}
