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
    public void createReport (Integer sender, Integer offender, ReportIDTO reportIDTO){
        Report report = new Report();
        if (reportIDTO.getOffenderType().equalsIgnoreCase("organization")){
          Organization organization= organizationRepository.findOrganizationById(offender)  ;
          if (organization==null){
               throw new ApiException("not found");
                }
                organization.getReports().add(report);
              organizationRepository.save(organization);}
        if (reportIDTO.getOffenderType().equalsIgnoreCase("researcher")){
            Researcher researcher= researcherRepository.findResearcherById(offender)  ;
            if (researcher==null){
                throw new ApiException("not found");
            }
            researcher.getReports().add(report);
            researcherRepository.save(researcher);}

        if (reportIDTO.getOffenderType().equalsIgnoreCase("contributor")){
            Contributor contributor= contributorRepository.findContributorById(offender)  ;
            if (contributor==null){
                throw new ApiException("not found");
            }
            contributor.getReports().add(report);
            contributorRepository.save(contributor);}
        report.setSender(sender);
        report.setReason(reportIDTO.getReason());
        report.setStatus("pending");
        report.setCreatedAt(LocalDate.now());
       reportRepository.save(report);
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
