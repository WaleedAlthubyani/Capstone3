package com.example.capstone3.Service;

import com.example.capstone3.Api.ApiException;
import com.example.capstone3.DTO.ArtifactIDTO;
import com.example.capstone3.DTO.ArtifactODTO;
import com.example.capstone3.Model.Artifact;
import com.example.capstone3.Model.Contributor;
import com.example.capstone3.Model.OwnershipHistory;
import com.example.capstone3.Repository.ArtifactRepository;
import com.example.capstone3.Repository.CategoryRepository;
import com.example.capstone3.Repository.ContributorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
//Mshari
@Service
@RequiredArgsConstructor
public class ArtifactService {
    private final ArtifactRepository artifactRepository;
    private final CategoryRepository categoryRepository;
    private final ContributorRepository contributorRepository;

    public List<ArtifactODTO> getArtifacts(){
        List<Artifact> artifacts = artifactRepository.findAllApprovedArtifacts();
        return convertArtifactsToDTO(artifacts);
    }

    public void updateArtifact(Integer artifactId, ArtifactIDTO artifactIDTO) {

        Artifact artifact = artifactRepository.findArtifactsById(artifactId);

        if (artifact == null) throw new ApiException("Artifact not found");

        Contributor currentContributor = artifact.getContributor();
        Contributor newContributor = contributorRepository.findContributorById(artifactIDTO.getContributorId());

        if (!currentContributor.getId().equals(newContributor.getId())) {

            LocalDate createdDate = currentContributor.getCreatedAt();
            LocalDate currentDate = LocalDate.now();
            Period ownershipPeriod = Period.between(createdDate, currentDate);

            OwnershipHistory ownershipHistory = new OwnershipHistory();
            ownershipHistory.setOwner(currentContributor.getName());
            ownershipHistory.setRecord(artifact.getRecord());
            ownershipHistory.setOwnershipPeriod(ownershipPeriod);

            artifact.getRecord().getOwnershipHistories().add(ownershipHistory);

            newContributor.setCreatedAt(LocalDate.now());
        }

        artifact.setName(artifactIDTO.getName());
        artifact.setType(artifactIDTO.getType());
        artifact.setOrigin(artifactIDTO.getOrigin());
        artifact.setEra(artifactIDTO.getEra());
        artifact.setLocation(artifactIDTO.getLocation());
        artifact.setCondition(artifactIDTO.getCondition());
        artifact.setCategory(categoryRepository.findCategoryById(artifactIDTO.getCategoryId()));
        artifact.setContributor(newContributor);

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

//Bayan
public void updateArtifactAvailability (Integer artifact_id , Integer contributor_id,Boolean availability){
        Artifact artifact =artifactRepository.findArtifactsById(artifact_id);
        if (artifact==null){
            throw new ApiException("Artifact not found");
        }
        if (!artifact.getContributor().getId().equals(contributor_id)){
            throw new ApiException("you not allow to update ");
        }
        artifact.setAvailability(availability);
}



}
