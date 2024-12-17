package com.example.capstone3.Service;

import com.example.capstone3.API.ApiException;
import com.example.capstone3.DTO.ContributorIDTO;
import com.example.capstone3.DTO.ContributorODTO;
import com.example.capstone3.Model.Contributor;
import com.example.capstone3.Repository.ContributorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContributorService {

    private final ContributorRepository contributorRepository;

    public List<ContributorODTO> getAllContributors(){
        return convertContributorsToDTO(contributorRepository.findAll());
    }

    public void addContributor(ContributorIDTO contributorIDTO){
        if (contributorRepository.existsByEmail(contributorIDTO.getEmail()))
            throw new ApiException("Email already exists");
        if (contributorRepository.existsByPhoneNumber(contributorIDTO.getPhoneNumber()))
            throw new ApiException("Phone number already exists");

        Contributor contributor=new Contributor();
        contributor.setName(contributorIDTO.getName());
        contributor.setEmail(contributorIDTO.getEmail());
        contributor.setPassword(contributorIDTO.getPassword());
        contributor.setPhoneNumber(contributorIDTO.getPhoneNumber());
        contributor.setCreatedAt(LocalDateTime.now());

        contributorRepository.save(contributor);
    }

    public void updateContributor(Integer id,ContributorIDTO contributorIDTO){
        Contributor contributor = contributorRepository.findContributorById(id);

        if (contributor==null)
            throw new ApiException("Contributor not found");

        if (!contributor.getEmail().equalsIgnoreCase(contributorIDTO.getEmail())){
            if (contributorRepository.existsByEmail(contributorIDTO.getEmail()))
                throw new ApiException("Email already exists");
        }

        if (!contributor.getPhoneNumber().equalsIgnoreCase(contributorIDTO.getPhoneNumber())){
            if (contributorRepository.existsByPhoneNumber(contributorIDTO.getPhoneNumber()))
                throw new ApiException("Phone number already exists");
        }

        contributor.setName(contributorIDTO.getName());
        contributor.setEmail(contributorIDTO.getEmail());
        contributor.setPassword(contributorIDTO.getPassword());
        contributor.setPhoneNumber(contributorIDTO.getPhoneNumber());

        contributorRepository.save(contributor);
    }

    public void deleteContributor(Integer id){
        Contributor contributor=contributorRepository.findContributorById(id);

        if (contributor==null)
            throw new ApiException("Contributor not found");

        contributorRepository.delete(contributor);
    }

    public List<ContributorODTO> convertContributorsToDTO(Collection<Contributor> contributors){
        List<ContributorODTO> contributorODTOS=new ArrayList<>();

        for (Contributor c:contributors){
            contributorODTOS.add(new ContributorODTO(c.getName(),c.getEmail(),c.getPhoneNumber(),c.getCreatedAt()));
        }

        return contributorODTOS;
    }
}
