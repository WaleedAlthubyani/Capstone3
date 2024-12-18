package com.example.capstone3.Service;

import com.example.capstone3.API.ApiException;
import com.example.capstone3.DTO.CertificateIDTO;
import com.example.capstone3.Model.Artifact;
import com.example.capstone3.Model.Certificate;
import com.example.capstone3.Repository.ArtifactRepository;
import com.example.capstone3.Repository.CertificateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CertificateService {
    private final CertificateRepository certificateRepository;
    private final ArtifactRepository artifactRepository;

    //Bayan
    public List<CertificateIDTO> getAll()  {
        return convertCertificateToDTo(certificateRepository.findAll());
    }
//Bayan
    public void addCertificate (Integer artifact_id, Certificate certificate ){
        Artifact artifact =artifactRepository.findArtifactsById(artifact_id);
        if (artifact==null){
            throw new ApiException("artifact not found");
        }
        certificate.setArtifact(artifact);
        certificateRepository.save(certificate);
    }
//Bayan
    public void updateCertificate (Integer id, Certificate certificate){
        Certificate old = certificateRepository.findCertificateById(id);
        if (old==null){
            throw new ApiException("id not found");
        }
        old.setName(certificate.getName());
        old.setType(certificate.getType());
        old.setGivingDate(certificate.getGivingDate());
        old.setExpirationDate(certificate.getExpirationDate());
        old.setRegistrationNumber(certificate.getRegistrationNumber());
        old.setUrl(certificate.getUrl());
        certificateRepository.save(old);

    }
//Bayan
    //scheduled task to delete expired certificates every day at midnight
@Scheduled(cron = "0 0 0 * * *")
    public  void deleteExpiredCertificate (){
        List<Certificate> expiredCertificate =certificateRepository.findByExpirationDateBefore(LocalDate.now());
        if(!expiredCertificate.isEmpty()){
            certificateRepository.deleteAll(expiredCertificate);

        }
    }
//Bayan
    public List<CertificateIDTO> convertCertificateToDTo (Collection<Certificate> certificates){
        List<CertificateIDTO> certificateDTOS = new ArrayList<>();
        for(Certificate c:certificates){
            certificateDTOS.add(new CertificateIDTO(c.getName(),c.getType(),c.getGivingDate(),
                    c.getExpirationDate(),c.getRegistrationNumber(),c.getUrl(),c.getArtifact().getId()));
        }
        return  certificateDTOS;
    }
}
