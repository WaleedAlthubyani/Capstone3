package com.example.capstone3.Service;

import com.example.capstone3.Api.ApiException;
import com.example.capstone3.DTO.RecordIDTO;
import com.example.capstone3.Model.Artifact;
import com.example.capstone3.Model.Contributor;
import com.example.capstone3.Model.Record;
import com.example.capstone3.Repository.ArtifactRepository;
import com.example.capstone3.Repository.ContributorRepository;
import com.example.capstone3.Repository.RecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
//Mshari
@Service
@RequiredArgsConstructor
public class RecordService {
    private final RecordRepository recordRepository;
    private final ArtifactRepository artifactRepository;
    private final ContributorRepository contributorRepository;



    public void updateRecord(Integer contributorId, Integer recordId, RecordIDTO recordIDTO) {
        Record existingRecord = recordRepository.findRecordById(recordId);
        if (existingRecord == null) throw new ApiException("Record not found");

        Contributor contributor = contributorRepository.findContributorById(contributorId);
        if (contributor == null) throw new ApiException("Contributor not found");

        Artifact artifact = artifactRepository.findArtifactsById(recordIDTO.getArtifact_id());
        if (artifact == null) throw new ApiException("Artifact not found");

        if (artifact.getContributor() != contributor) throw new ApiException("Contributor is not the owner of the artifact");

        existingRecord.setArtifact(artifact);
        existingRecord.setPurpose(recordIDTO.getPurpose());
        existingRecord.setDescription(recordIDTO.getDescription());

        recordRepository.save(existingRecord);
    }

}
