package com.example.capstone3.Service;

import com.example.capstone3.API.ApiException;
import com.example.capstone3.Model.Researcher;
import com.example.capstone3.Repository.AdminRepository;
import com.example.capstone3.Repository.ResearcherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;
    private final ResearcherRepository researcherRepository;

    public List<Researcher> getPendingResearchers(Integer id){

        if (adminRepository.findAdminById(id)==null){
            throw new ApiException("admin not found");
        }

        return researcherRepository.findAllPendingResearcher();
    }

    public void decideOnResearcher(Integer adminId,Integer researcherId,String decision){
        if (adminRepository.findAdminById(adminId)==null)
            throw new ApiException("Admin not found");

        Researcher researcher=researcherRepository.findResearcherById(researcherId);

        if (researcher==null)
            throw new ApiException("researcher not found");

        if (decision.equalsIgnoreCase("rejected")){
            researcher.setStatus("rejected");
            researcherRepository.save(researcher);
            return;
        }

        researcher.setStatus("approved");
        researcherRepository.save(researcher);
    }
}
