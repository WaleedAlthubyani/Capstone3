package com.example.capstone3.Service;

import com.example.capstone3.API.ApiException;
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
import java.util.List;

@Service
@RequiredArgsConstructor
public class RequestService {

    private final RequestRepository requestRepository;
    private final ContributorRepository contributorRepository;

    public List<RequestODTO> getContributorRequests(Contributor contributor){
        return convertRequestsToDTO(requestRepository.findRequestsByContributor(contributor));
    }

    public void addRequest(Integer contributorId,Object requester,String type,RequestIDTO request){
        Request request1=new Request();
        request1.setType(type);
        request1.setContributor(contributorRepository.findContributorById(contributorId));
        request1.setStartDate(request.getStartDate());
        request1.setEndDate(request.getEndDate());

        if (requester.getClass().equals(Researcher.class)){
            request1.setResearcher((Researcher) requester);
        }else if (requester.getClass().equals(Organization.class)){
            request1.setOrganization((Organization) requester);
        }

        requestRepository.save(request1);
    }

    public void updateRequest(Integer id, Request request){

        if (!request.getDecision().equalsIgnoreCase("pending")){
            throw new ApiException("can't update a request already decided on");
        }

        Request oldRequest=requestRepository.findRequestById(id);

        if (oldRequest==null) throw new ApiException("Request not found");


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
}
