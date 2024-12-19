package com.example.capstone3.Service;

import com.example.capstone3.Api.ApiException;
import com.example.capstone3.DTO.*;
import com.example.capstone3.Model.Organization;
import com.example.capstone3.Model.Request;
import com.example.capstone3.Repository.OrganizationRepository;
import com.example.capstone3.Repository.ReportRepository;
import com.example.capstone3.Repository.RequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrganizationService {
    private final OrganizationRepository organizationRepository;
    private final FeedbackService feedbackService;
    private final RequestRepository requestRepository;
    private final RequestService requestService;
    private final ReportService reportService;
    private final ReportRepository reportRepository;

//Bayan
    public List<OrganizationODTO> getAllOrganizations()  {
        List<Organization> organization = organizationRepository.findByStatusIsNot("pending");
        return convertOrganizationToDTo(organization);
    }
//Bayan
    public void add (OrganizationIDTO organizationIDTO){
        Organization organization = new Organization();
        organization.setName(organizationIDTO.getName());
        organization.setEmail(organizationIDTO.getEmail());
        organization.setPhoneNumber(organizationIDTO.getPhoneNumber());
        organization.setType(organizationIDTO.getType());
        organization.setPassword(organizationIDTO.getPassword());
        organization.setCreatedAt(LocalDate.now());
        organizationRepository.save(organization);
    }
//Bayan
    public void update (Integer id ,Organization organization){
        Organization old=organizationRepository.findOrganizationById(id);
        if (old==null){
            throw new ApiException("organization id not found");
        }
        if (old.getIsBanned())
            throw new ApiException("organization is banned");
        old.setName(organization.getName());
        old.setEmail(organization.getEmail());
        old.setType(organization.getType());
        old.setPassword(organization.getPassword());
        old.setPhoneNumber(organization.getPhoneNumber());

        organizationRepository.save(old);
    }
//Bayan
    public void delete (Integer id){
        Organization organization=organizationRepository.findOrganizationById(id);
        if (organization==null){
            throw new ApiException("organization id not found");
        }
        organizationRepository.delete(organization);
    }
//Bayan
    public  List<OrganizationODTO> convertOrganizationToDTo (Collection<Organization> organizations){
        List<OrganizationODTO> organizationODTOs = new ArrayList<>();
for(Organization o:organizations){
    if (o.getIsBanned())
        continue;
    organizationODTOs.add(new OrganizationODTO(o.getName(), o.getType(), o.getPhoneNumber(), o.getEmail(), o.getEvents(),o.getCreatedAt()));
}
return  organizationODTOs;
    }
//Waleed
    public void giveFeedback(Integer requestId, FeedbackDTO feedbackDTO) {
        Request request = requestRepository.findRequestById(requestId);
        if (request==null) throw new ApiException("Request not found");

        if (request.getOrganization().getIsBanned())
            throw new ApiException("Organization is banned");
        feedbackDTO.setSenderEmail(request.getOrganization().getEmail());

        feedbackDTO.setReceiverEmail(request.getContributor().getEmail());

        feedbackDTO.setCreatorType("organization");
        feedbackDTO.setCreatorId(request.getOrganization().getId());
        feedbackDTO.setOrganization(request.getOrganization());
        feedbackDTO.setContributor(request.getContributor());

        feedbackService.createFeedback(requestId, feedbackDTO);
    }
//Bayan
    public void updateRequest (Integer organization_id ,Integer id, Request request ){
        Organization organization = organizationRepository.findOrganizationById(organization_id);
        if (organization== null || !request.getOrganization().getId().equals(organization_id)){
            throw new ApiException("organization not found");
        }
        if (organization.getIsBanned())
            throw new ApiException("organization is banned");
        requestService.updateRequest(id,request);
    }
//Bayan
    public void  deleteRequest ( Integer organization_id ,Integer id){
        Organization organization = organizationRepository.findOrganizationById(organization_id);
        if (organization== null ){
            throw new ApiException("organization not found");
        }
        if (organization.getIsBanned())
            throw new ApiException("organization is banned");
        requestService.deleteRequest(id,organization);
    }
//Bayan
    public void report (Integer organization_id,Integer offender, ReportIDTO reportIDTO){
        Organization organization = organizationRepository.findOrganizationById(organization_id);
        if(organization==null){
            throw new ApiException("organization not found");
        }
        if (organization.getIsBanned())
            throw new ApiException("organization is banned");
        reportService.createReport(organization_id,offender,reportIDTO);
    }
//Bayan
    public List<ReportODTO> getReportsSentByOrganization (Integer organization_id){
        Organization organization =organizationRepository.findOrganizationById(organization_id);
        if(organization==null){
            throw new ApiException("organization not found");
        }
        if (organization.getIsBanned())
            throw new ApiException("organization is banned");

        return reportService.convertReportToDTo(reportRepository.findAllBySender(organization_id));
    }
}
