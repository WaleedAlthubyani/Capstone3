package com.example.capstone3.Service;

import com.example.capstone3.ApiRespose.ApiException;
import com.example.capstone3.Model.Organization;
import com.example.capstone3.Repository.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class OrganizationService {
    private final OrganizationRepository organizationRepository;

    public void add (Organization organization){
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
        old.setCreatedAt(LocalDate.now());

        organizationRepository.save(old);
    }


    public void delete (Integer id){
        Organization organization=organizationRepository.findOrganizationById(id);
        if (organization==null){
            throw new ApiException("organization id not found");
        }
        organizationRepository.delete(organization);
    }
}
