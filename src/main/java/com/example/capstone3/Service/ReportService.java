package com.example.capstone3.Service;

import com.example.capstone3.Api.ApiException;
import com.example.capstone3.DTO.OrganizationODTO;
import com.example.capstone3.DTO.ReportIDTO;
import com.example.capstone3.DTO.ReportODTO;
import com.example.capstone3.Model.Contributor;
import com.example.capstone3.Model.Organization;
import com.example.capstone3.Model.Report;
import com.example.capstone3.Model.Researcher;
import com.example.capstone3.Repository.ContributorRepository;
import com.example.capstone3.Repository.OrganizationRepository;
import com.example.capstone3.Repository.ReportRepository;
import com.example.capstone3.Repository.ResearcherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportService {
    private final ReportRepository reportRepository;
    private final OrganizationRepository organizationRepository;
    private final ResearcherRepository researcherRepository;
    private final ContributorRepository contributorRepository;

    //Bayan
    public void createReport (ReportIDTO reportIDTO){
        addReportToEntity(reportIDTO.getSender());
        addReportToEntity(reportIDTO.getOffender());
        Report report = new Report();
        report.setSender(reportIDTO.getSender());
        report.setOffender(reportIDTO.getOffender());
        report.setReason(reportIDTO.getReason());
        report.setStatus("pending");
        report.setCreatedAt(LocalDate.now());
       reportRepository.save(report);
    }
//Bayan
    private void addReportToEntity(Integer entityId) {
        if (organizationRepository.existsById(entityId)) {
            Organization organization = organizationRepository.findOrganizationById(entityId);
            organization.getReports().add(new Report()); // ربط البلاغ بالمنظمة
            organizationRepository.save(organization);
        } else if (contributorRepository.existsById(entityId)) {
            Contributor contributor = contributorRepository.findContributorById(entityId);
            contributor.getReports().add(new Report());
            contributorRepository.save(contributor);
        } else if (researcherRepository.existsById(entityId)) {
            Researcher researcher = researcherRepository.findResearcherById(entityId);
            researcher.getReports().add(new Report());
            researcherRepository.save(researcher);
        }
        throw new ApiException("entity ID not found");
    }
//Bayan
    public List<ReportODTO> convertReportToDTo (Collection<Report> reports){
        List<ReportODTO> reportODTOS = new ArrayList<>();
        for(Report r:reports){
            reportODTOS.add(new ReportODTO(r.getSender(),r.getOffender(),r.getReason(),r.getStatus(),r.getCreatedAt(),r.getDecision()));
        }
        return  reportODTOS;
    }
}
