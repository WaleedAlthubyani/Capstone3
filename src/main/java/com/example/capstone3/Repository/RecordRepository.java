package com.example.capstone3.Repository;

import com.example.capstone3.Model.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
//Mshari
@Repository
public interface RecordRepository extends JpaRepository<Record, Integer> {
    Record findRecordById(Integer id);
}
