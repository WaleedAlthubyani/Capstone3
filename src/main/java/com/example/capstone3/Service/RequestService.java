package com.example.capstone3.Service;

import com.example.capstone3.Api.ApiException;
import com.example.capstone3.Model.*;
import com.example.capstone3.Repository.ArtifactRepository;
import com.example.capstone3.Repository.OrganizationRepository;
import com.example.capstone3.Repository.RequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import java.util.List;
//Waleed
@Service
@RequiredArgsConstructor
public class RequestService {

    private final RequestRepository requestRepository;
    private final OrganizationRepository organizationRepository;
    private final ArtifactRepository artifactRepository;


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

//Bayan
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
