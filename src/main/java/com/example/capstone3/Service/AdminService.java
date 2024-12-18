package com.example.capstone3.Service;

import com.example.capstone3.API.ApiException;
import com.example.capstone3.Model.Artifact;
import com.example.capstone3.Model.BanList;
import com.example.capstone3.Model.Organization;
import com.example.capstone3.Model.Researcher;
import com.example.capstone3.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;
    private final ResearcherRepository researcherRepository;
    private final ArtifactRepository artifactRepository;
    private final OrganizationRepository organizationRepository;
    private final BanListRepository banListRepository;

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
            throw new ApiException("Researcher not found");

        if (decision.equalsIgnoreCase("rejected")){
            researcher.setStatus("rejected");
            researcherRepository.save(researcher);
        } else if (decision.equalsIgnoreCase("approved")) {
            researcher.setStatus("approved");
            researcherRepository.save(researcher);
        }else {
            throw new ApiException("decision can only be 'rejected' or 'approved'");
        }
    }

    public List<Organization> getPendingOrganizations(Integer id){

        if (adminRepository.findAdminById(id)==null){
            throw new ApiException("admin not found");
        }

        return organizationRepository.findAllPendingOrganizations();
    }

    public void decideOnOrganization(Integer adminId,Integer organizationId,String decision){
        if (adminRepository.findAdminById(adminId)==null)
            throw new ApiException("Admin not found");

        Organization organization=organizationRepository.findOrganizationById(organizationId);

        if (organization==null)
            throw new ApiException("Organization not found");

        if (decision.equalsIgnoreCase("rejected")){
            organization.setStatus("rejected");
            organizationRepository.save(organization);
        }else if (decision.equalsIgnoreCase("approved")){
            organization.setStatus("approved");
            organizationRepository.save(organization);
        }else {
            throw new ApiException("decision can only be 'rejected' or 'approved'");
        }

    }

    public List<Artifact> getPendingArtifacts(Integer id){

            if (adminRepository.findAdminById(id)==null){
                throw new ApiException("admin not found");
            }

            return artifactRepository.findAllPendingArtifacts();
        }

        public void decideOnArtifact(Integer adminId,Integer artifactId,String decision){
            if (adminRepository.findAdminById(adminId)==null)
                throw new ApiException("Admin not found");

            Artifact artifact=artifactRepository.findArtifactsById(artifactId);

            if (artifact==null)
                throw new ApiException("Artifact not found");

            if (decision.equalsIgnoreCase("rejected")){
                artifact.setStatus("rejected");
                artifactRepository.save(artifact);
            }else if (decision.equalsIgnoreCase("approved")){
                artifact.setStatus("approved");
                artifactRepository.save(artifact);
            }else {
                throw new ApiException("decision can only be 'rejected' or 'approved'");
            }

        }

//Bayan
        public List<Object> getBanList (){
        List<Object> banList=new ArrayList<>();
        List<BanList> banLists =banListRepository.findAll();
        for(BanList b: banLists){
            banList.addAll(b.getOrganizations());
            banList.addAll(b.getContributors());
            banList.addAll(b.getResearchers());
        }return banList;
        }
}
