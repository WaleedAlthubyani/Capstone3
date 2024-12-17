package com.example.capstone3.Service;

import com.example.capstone3.Model.Contributor;
import com.example.capstone3.Model.Request;
import com.example.capstone3.Repository.RequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RequestService {

    private final RequestRepository requestRepository;

    public List<Request> getContributorRequests(Contributor contributor){
        return requestRepository.findRequestsByContributor(contributor);
    }

    public void addRequest(Request request){
        requestRepository.save(request);
    }

    public void updateRequest(Integer id, Request request){

//        if (!request.getDecision().equalsIgnoreCase("pending")){
//            throw new ApiException("can't update a request already decided on");
//            return;
//        }

        Request oldRequest=requestRepository.findRequestById(id);

//        if (oldRequest==null) throw new ApiException("Request not found");


        oldRequest.setType(request.getType());
        oldRequest.setStartDate(request.getStartDate());
        oldRequest.setEndDate(request.getEndDate());
        oldRequest.setContributor(request.getContributor());

        requestRepository.save(oldRequest);
    }

    public void deleteRequest(Integer requestId,Integer requesterId){
        Request request=requestRepository.findRequestById(requestId);

//        if (request==null) throw new ApiException("Request not found");

//        if (!request.getDecision().equalsIgnoreCase("pending")){
//            throw new ApiException("can't update a request already decided on");
//            return;
//        }


        requestRepository.delete(request);
    }
}
