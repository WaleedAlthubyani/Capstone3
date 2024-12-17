package com.example.capstone3.Service;

import com.example.capstone3.API.ApiException;
import com.example.capstone3.DTO.OwnershipHistoryIDTO;
import com.example.capstone3.Model.OwnershipHistory;
import com.example.capstone3.Model.Record;
import com.example.capstone3.Repository.OwnershipHistoryRepository;
import com.example.capstone3.Repository.RecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OwnershipHistoryService {

    private final OwnershipHistoryRepository ownershipHistoryRepository;
    private final RecordRepository recordRepository;

    public void addOwnershipHistory(OwnershipHistoryIDTO ownershipHistoryIDTO) {
        Record record = recordRepository.findRecordById(ownershipHistoryIDTO.getRecordId());

        if (record == null) throw new ApiException("Record not found!");

        OwnershipHistory ownershipHistory = new OwnershipHistory();
        ownershipHistory.setOwner(ownershipHistoryIDTO.getOwner());
        ownershipHistory.setOwnershipPeriod(ownershipHistoryIDTO.getOwnershipPeriod());
        ownershipHistory.setRecord(record);

        ownershipHistoryRepository.save(ownershipHistory);
    }

    public void updateOwnershipHistory(Integer id, OwnershipHistoryIDTO ownershipHistoryIDTO) {
        OwnershipHistory ownershipHistory = ownershipHistoryRepository.findOwnershipHistoriesById(id);
        Record record = recordRepository.findRecordById(ownershipHistoryIDTO.getRecordId());

        if (record == null) throw new ApiException("Record not found!");
        if (ownershipHistory == null) throw new ApiException("Ownership history not found!");

        ownershipHistory.setOwner(ownershipHistoryIDTO.getOwner());
        ownershipHistory.setOwnershipPeriod(ownershipHistoryIDTO.getOwnershipPeriod());
        ownershipHistory.setRecord(record);

        ownershipHistoryRepository.save(ownershipHistory);
    }

    public void deleteOwnershipHistory(Integer id) {
        OwnershipHistory ownershipHistory = ownershipHistoryRepository.findOwnershipHistoriesById(id);

        if (ownershipHistory == null) throw new ApiException("Ownership history not found!");

        ownershipHistoryRepository.delete(ownershipHistory);
    }
}
