package com.example.capstone3.Repository;

import com.example.capstone3.Model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
//Bayan
@Repository
public interface ReportRepository extends JpaRepository<Report,Integer> {
    Report findReportById (Integer id);
    List<Report> findAllBySender (Integer sender);
}
