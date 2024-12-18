package com.example.capstone3.Service;

import com.example.capstone3.API.ApiException;
import com.example.capstone3.DTO.RecordIDTO;
import com.example.capstone3.Model.Record;
import com.example.capstone3.Model.Artifact;
import com.example.capstone3.Repository.ArtifactRepository;
import com.example.capstone3.Repository.RecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecordService {
    private final RecordRepository recordRepository;
    private final ArtifactRepository artifactRepository;

    public void addRecord(RecordIDTO recordIDTO) {
        Artifact artifact = artifactRepository.findArtifactsById(recordIDTO.getArtifact_id());

        if (artifact == null) throw new ApiException("Artifact not found");

        Record record = new Record();
        record.setArtifact(artifact);
        record.setPurpose(recordIDTO.getPurpose());
        record.setDescription(recordIDTO.getDescription());

        recordRepository.save(record);
    }

    public void updateRecord(Integer id, RecordIDTO recordIDTO) {
        Record existingRecord = recordRepository.findRecordById(id);
        if (existingRecord == null) throw new ApiException("Record not found");

        Artifact artifact = artifactRepository.findArtifactsById(recordIDTO.getArtifact_id());
        if (artifact == null) throw new ApiException("Artifact not found");

        existingRecord.setArtifact(artifact);
        existingRecord.setPurpose(recordIDTO.getPurpose());
        existingRecord.setDescription(recordIDTO.getDescription());

        recordRepository.save(existingRecord);
    }

}
