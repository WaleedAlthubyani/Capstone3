package com.example.capstone3.Service;

import com.example.capstone3.API.ApiException;
import com.example.capstone3.DTO.ArtifactIDTO;
import com.example.capstone3.DTO.ArtifactODTO;
import com.example.capstone3.Model.Artifact;
import com.example.capstone3.Model.Certificate;
import com.example.capstone3.Repository.ArtifactRepository;
import com.example.capstone3.Repository.CategoryRepository;
import com.example.capstone3.Repository.CertificateRepository;
import com.example.capstone3.Repository.ContributorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ArtifactService {
    private final ArtifactRepository artifactRepository;
    private final CategoryRepository categoryRepository;
    private final ContributorRepository contributorRepository;
    private final CertificateRepository certificateRepository;

    public List<ArtifactODTO> getArtifacts(){
        List<Artifact> artifacts = artifactRepository.findAllApprovedArtifacts();
        return convertArtifactsToDTO(artifacts);
    }

    public void addArtifact(ArtifactIDTO artifactIDTO){
        Artifact artifact = new Artifact();

        artifact.setName(artifactIDTO.getName());
        artifact.setType(artifactIDTO.getType());
        artifact.setOrigin(artifactIDTO.getOrigin());
        artifact.setEra(artifactIDTO.getEra());
        artifact.setLocation(artifactIDTO.getLocation());
        artifact.setCondition(artifactIDTO.getCondition());
        artifact.setCategory(categoryRepository.findCategoryById(artifactIDTO.getCategoryId()));
        artifact.setContributor(contributorRepository.findContributorById(artifactIDTO.getContributorId()));

        artifactRepository.save(artifact);
    }

    public void updateArtifact(Integer artifactId, ArtifactIDTO artifactIDTO){

        Artifact artifact = artifactRepository.findArtifactsById(artifactId);

        if (artifact == null) throw new ApiException("Artifact not found");

//        if (!artifact.getContributor().equals(contributorRepository.findContributorById(artifactIDTO.getContributorId()))){
//            artifact.getRecord().getOwnershipHistories().add(artifact.getContributor().getName())
//        }

        artifact.setName(artifactIDTO.getName());
        artifact.setType(artifactIDTO.getType());
        artifact.setOrigin(artifactIDTO.getOrigin());
        artifact.setEra(artifactIDTO.getEra());
        artifact.setLocation(artifactIDTO.getLocation());
        artifact.setCondition(artifactIDTO.getCondition());
        artifact.setCategory(categoryRepository.findCategoryById(artifactIDTO.getCategoryId()));
        artifact.setContributor(contributorRepository.findContributorById(artifactIDTO.getContributorId()));
        artifactRepository.save(artifact);

    }

    public void deleteArtifact(Integer artifactId, Integer contributorId){
        Artifact artifact = artifactRepository.findArtifactsById(artifactId);

        if (artifact == null) throw new ApiException("Artifact not found");
        if (contributorId == null) throw new ApiException("Contributor not found");

        artifact.setCertificates(null);
        artifact.setContributor(null);

        artifactRepository.delete(artifact);
    }


    public List<ArtifactODTO> convertArtifactsToDTO(Collection<Artifact> artifacts){
        List<ArtifactODTO> artifactsDTO = new ArrayList<>();
        for(Artifact artifact : artifacts){
            artifactsDTO.add(new ArtifactODTO(artifact.getName(),artifact.getType(),artifact.getOrigin(),artifact.getEra(),artifact.getLocation(),artifact.getCondition()
                    ,artifact.getAvailability(),artifact.getImages(),artifact.getTags(),artifact.getCategory(),artifact.getCertificates(),artifact.getContributor().getName()));
        }
        return artifactsDTO;
    }
}
