package com.example.capstone3.Service;

import com.example.capstone3.API.ApiException;
import com.example.capstone3.Model.*;
import com.example.capstone3.Repository.ArtifactRepository;
import com.example.capstone3.Repository.OrganizationRepository;
import com.example.capstone3.DTO.RequestIDTO;
import com.example.capstone3.DTO.RequestODTO;
import com.example.capstone3.Model.Contributor;
import com.example.capstone3.Model.Organization;
import com.example.capstone3.Model.Request;
import com.example.capstone3.Model.Researcher;
import com.example.capstone3.Repository.ContributorRepository;
import com.example.capstone3.Repository.RequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RequestService {

    private final RequestRepository requestRepository;
    private final OrganizationRepository organizationRepository;
    private final ArtifactRepository artifactRepository;
    private final ContributorRepository contributorRepository;

    public List<Request> getContributorRequests(Contributor contributor){
        return requestRepository.findRequestsByContributor(contributor);
    }

    public void addRequest(Request request){
        requestRepository.save(request);
    }

    public void updateRequest(Integer id, Request request){
        Request oldRequest=requestRepository.findRequestById(id);

        if (oldRequest==null) throw new ApiException("Request not found");

        if (!oldRequest.getDecision().equalsIgnoreCase("pending")){
            throw new ApiException("can't update a request already decided on");
        }

        oldRequest.setType(request.getType());
        oldRequest.setStartDate(request.getStartDate());
        oldRequest.setEndDate(request.getEndDate());
        oldRequest.setContributor(request.getContributor());

        requestRepository.save(oldRequest);
    }

    public void deleteRequest(Integer requestId,Object requester){
        Request request=requestRepository.findRequestById(requestId);

        if (request==null) throw new ApiException("Request not found");

        if (requester.getClass().equals(Researcher.class) && !request.getResearcher().equals(requester)){
            throw new ApiException("Can't delete a request from someone else");
        }

        if (requester.getClass().equals(Organization.class) && !request.getOrganization().equals(requester)){
            throw new ApiException("Can't delete a request from someone else");
        }

        if (!request.getDecision().equalsIgnoreCase("pending")){
            throw new ApiException("can't delete a request already decided on");
        }


        requestRepository.delete(request);
    }

    public List<RequestODTO> convertRequestsToDTO(List<Request> requests){
        List<RequestODTO> convertedRequests=new ArrayList<>();

        Object requester=null;

        for (Request request:requests){
            if (request.getOrganization()!=null)
                requester=request.getOrganization();
            else if (request.getResearcher()!=null)
                requester=request.getResearcher();

            convertedRequests.add(new RequestODTO(requester,request.getType(),request.getStartDate(),request.getEndDate(),request.getDecision(),request.getCreatedAt()));
        }

        return convertedRequests;
    }

    public void requestBorrowArtifact (Integer organization_id,Integer artifact_id ,RequestIDTO requestIDTO){
        Organization organization = organizationRepository.findOrganizationById(organization_id);
        if (organization==null){
            throw new ApiException("organization not found");
        }
        Artifact artifact =artifactRepository.findArtifactsById(artifact_id);
        if (artifact==null){
            throw new ApiException("artifact not found");
        }
        Contributor contributor =artifact.getContributor();
        if (contributor==null){
            throw new ApiException("contributor not found");
        }
        Request request=new Request();
        request.setStartDate(requestIDTO.getStartDate());
        request.setEndDate(requestIDTO.getEndDate());
        request.setOrganization(organization);
        request.setContributor(contributor);
        request.setType("exhibit");
        request.setDecision("pending");
        request.setCreatedAt(LocalDateTime.now());
        requestRepository.save(request);
    }
}
