package com.example.capstone3.Service;

import com.example.capstone3.DTO.ResearcherIDTO;
import com.example.capstone3.DTO.ResearcherODTO;
import com.example.capstone3.Model.Researcher;
import com.example.capstone3.Repository.ResearcherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ResearcherService {

    private final ResearcherRepository researcherRepository;

    public List<ResearcherODTO> getAllResearchers(){
        return convertResearcherToDTO(researcherRepository.findAllApprovedResearcher());
    }

    public void addResearcher(ResearcherIDTO researcherIDTO){
//        if (researcherRepository.existsByEmail(researcherIDTO.getEmail()))
//            throw new ApiException("Email already exists");
//        if (researcherRepository.existsByPhoneNumber(researcherIDTO.getPhoneNumber()))
//            throw new ApiException("Phone number already exists");
//        if (researcherRepository.existsByLicenseURL(researcherIDTO.getLicenseURL()))
//            throw new ApiException("License already in use");

        Researcher researcher=new Researcher();
        researcher.setName(researcherIDTO.getName());
        researcher.setEmail(researcherIDTO.getEmail());
        researcher.setPassword(researcherIDTO.getPassword());
        researcher.setLicenseURL(researcher.getLicenseURL());
        researcher.setCreatedAt(LocalDateTime.now());

        researcherRepository.save(researcher);
    }

    public void updateResearcher(Integer id,ResearcherIDTO researcherIDTO){
        Researcher researcher=researcherRepository.findResearcherById(id);

//        if (researcher==null) throw new ApiException("Researcher not found");

//        if (!researcher.getEmail().equalsIgnoreCase(researcherIDTO.getEmail())){
//            if (researcherRepository.existsByEmail(researcherIDTO.getEmail()))
//                throw new ApiException("Email already exists");
//        }
//
//        if (!researcher.getPhoneNumber().equals(researcherIDTO.getPhoneNumber())){
//            if (researcherRepository.existsByPhoneNumber(researcherIDTO.getPhoneNumber()))
//                throw new ApiException("Phone number already exists");
//        }
//
//        if (!researcher.getLicenseURL().equals(researcherIDTO.getLicenseURL())){
//            researcher.setStatus("pending");
//            if (researcherRepository.existsByLicenseURL(researcherIDTO.getLicenseURL())){
//                throw new ApiException("License already exists");
//            }
//        }

        researcher.setName(researcherIDTO.getName());
        researcher.setEmail(researcherIDTO.getEmail());
        researcher.setPassword(researcherIDTO.getPassword());
        researcher.setPhoneNumber(researcherIDTO.getPhoneNumber());
        researcher.setLicenseURL(researcherIDTO.getLicenseURL());
        researcher.setFieldOfStudy(researcherIDTO.getFieldOfStudy());

        researcherRepository.save(researcher);
    }

    public void deleteResearcher(Integer id){
        Researcher researcher=researcherRepository.findResearcherById(id);

//        if (researcher==null) throw new ApiException("Researcher not found");

        researcherRepository.delete(researcher);
    }

    public List<ResearcherODTO> convertResearcherToDTO(Collection<Researcher> researchers){
        List<ResearcherODTO> researcherODTOS=new ArrayList<>();

        for (Researcher r:researchers){
            researcherODTOS.add(new ResearcherODTO(r.getName(),r.getEmail(),r.getPhoneNumber(),r.getFieldOfStudy(),r.getLicenseURL()));
        }

        return researcherODTOS;
    }
}
