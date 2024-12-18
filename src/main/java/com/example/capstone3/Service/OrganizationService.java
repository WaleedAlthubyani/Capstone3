package com.example.capstone3.Service;

import com.example.capstone3.API.ApiException;
import com.example.capstone3.DTO.FeedbackDTO;
import com.example.capstone3.DTO.OrganizationIDTO;
import com.example.capstone3.DTO.OrganizationODTO;
import com.example.capstone3.Model.Organization;
import com.example.capstone3.Model.Request;
import com.example.capstone3.Repository.OrganizationRepository;
import com.example.capstone3.Repository.RequestRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
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


    public List<OrganizationODTO> getAllOrganizations()  {
        List<Organization> organization = organizationRepository.findByStatusIsNot("pending");
        return convertOrganizationToDTo(organization);
    }

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

    public void update (Integer id ,Organization organization){
        Organization old=organizationRepository.findOrganizationById(id);
        if (old==null){
            throw new ApiException("organization id not found");
        }
        old.setName(organization.getName());
        old.setEmail(organization.getEmail());
        old.setType(organization.getType());
        old.setPassword(organization.getPassword());
        old.setPhoneNumber(organization.getPhoneNumber());

        organizationRepository.save(old);
    }

    public void delete (Integer id){
        Organization organization=organizationRepository.findOrganizationById(id);
        if (organization==null){
            throw new ApiException("organization id not found");
        }
        organizationRepository.delete(organization);
    }

    public  List<OrganizationODTO> convertOrganizationToDTo (Collection<Organization> organizations){
        List<OrganizationODTO> organizationODTOs = new ArrayList<>();
for(Organization o:organizations){
    organizationODTOs.add(new OrganizationODTO(o.getName(),o.getType(),o.getPhoneNumber(),o.getEmail(),o.getCreatedAt()));
}
return  organizationODTOs;
    }

    public void giveFeedback(Integer requestId, FeedbackDTO feedbackDTO) {
        Request request = requestRepository.findRequestById(requestId);
        if (request==null) throw new ApiException("Request not found");

        feedbackDTO.setSenderId(request.getOrganization().getId());

        if (request.getContributor()==null){
            feedbackDTO.setReceiverId(request.getResearcher().getId());
        } else if (request.getResearcher()==null) {
            feedbackDTO.setReceiverId(request.getContributor().getId());
        }

        feedbackDTO.setCreatorType("organization");
        feedbackDTO.setCreatorId(request.getOrganization().getId());

        feedbackService.createFeedback(requestId, feedbackDTO);
    }

    public void updateRequest (Integer organization_id ,Integer id, Request request ){
        Organization organization = organizationRepository.findOrganizationById(organization_id);
        if (organization== null && request.getOrganization().getId().equals(organization_id)){
            throw new ApiException("organization not found");
        }
        requestService.updateRequest(id,request);
    }

    public void  deleteRequest ( Integer organization_id ,Integer id){
        Organization organization = organizationRepository.findOrganizationById(organization_id);
        if (organization== null ){
            throw new ApiException("organization not found");
        }
        requestService.deleteRequest(id,organization);
    }
}
