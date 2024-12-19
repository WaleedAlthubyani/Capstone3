package com.example.capstone3.Service;

import com.example.capstone3.Api.ApiException;
import com.example.capstone3.DTO.*;
import com.example.capstone3.Model.Record;
import com.example.capstone3.Model.*;
import com.example.capstone3.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContributorService {

    private final ContributorRepository contributorRepository;
    private final ArtifactRepository artifactRepository;
    private final TagRepository tagRepository;
    private final CategoryRepository categoryRepository;
    private final OwnershipHistoryRepository ownershipHistoryRepository;
    private final CertificateRepository certificateRepository;
    private final RequestRepository requestRepository;
    private final FeedbackService feedbackService;
    private final  FeedbackRepository feedbackRepository;
    private final RecordRepository recordRepository;
    private final ReportService reportService;
    private final ReportRepository reportRepository;

    //Waleed
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
        contributor.setCreatedAt(LocalDate.now());

        contributorRepository.save(contributor);
    }

    public void updateContributor(Integer id,ContributorIDTO contributorIDTO){
        Contributor contributor = contributorRepository.findContributorById(id);

        if (contributor==null)
            throw new ApiException("Contributor not found");
        if (contributor.getIsBanned())
            throw new ApiException("Contributor is banned");

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
            if (c.getIsBanned()) continue;
            contributorODTOS.add(new ContributorODTO(c.getName(),c.getEmail(),c.getPhoneNumber(),c.getCreatedAt()));
        }

        return contributorODTOS;
    }



    //Mshari
    public void addArtifact(ArtifactIDTO artifactIDTO) {

        if(contributorRepository.findContributorById(artifactIDTO.getContributorId())==null) throw new ApiException("Contributor not found");

        Artifact artifact = new Artifact();

        artifact.setName(artifactIDTO.getName());
        artifact.setType(artifactIDTO.getType());
        artifact.setOrigin(artifactIDTO.getOrigin());
        artifact.setEra(artifactIDTO.getEra());
        artifact.setLocation(artifactIDTO.getLocation());
        artifact.setCondition(artifactIDTO.getCondition());
        artifact.setContributor(contributorRepository.findContributorById(artifactIDTO.getContributorId()));
        artifact.setStatus("pending");
        artifact.setAvailability(true);

        artifactRepository.save(artifact);
    }

    public void assignTag(Integer artifactId, Integer tagId) {
        Artifact artifact = artifactRepository.findArtifactsById(artifactId);
        if (artifact==null) throw new ApiException("Artifact not found");

        Tag tag = tagRepository.findTagById(tagId);
        if (tag==null) throw new ApiException("Tag not found");

        artifact.getTags().add(tag);
        artifactRepository.save(artifact);
    }

    public void assignCategory(Integer artifactId, Integer categoryId) {
        Artifact artifact = artifactRepository.findArtifactsById(artifactId);
        if (artifact==null) throw new ApiException("Artifact not found");

        Category category = categoryRepository.findCategoryById(categoryId);
        if (category==null) throw new ApiException("Category not found");

        artifact.setCategory(category);
        artifactRepository.save(artifact);
    }

    public void addRecord(Integer contributorID, RecordIDTO recordIDTO){
        Artifact artifact = artifactRepository.findArtifactsById(recordIDTO.getArtifact_id());
        if (artifact == null) throw new ApiException("Artifact not found");
        Contributor contributor = contributorRepository.findContributorById(contributorID);
        if (contributor == null) throw new ApiException("Contributor not found");
        if (artifact.getContributor() != contributor) throw new ApiException("Contributor is not the owner of the artifact");

        Record record = new Record(null,recordIDTO.getPurpose(),recordIDTO.getDescription(),artifact,null);

        recordRepository.save(record);
    }

    public void addImage(Integer artifactId, String url, String description) {
        Artifact artifact = artifactRepository.findArtifactsById(artifactId);
        if (artifact==null) throw new ApiException("Artifact not found");

        artifact.getImages().add(new Image(null, url,description,artifact));
        artifactRepository.save(artifact);
    }

    public void addOwnershipHistory(Integer artifactId, OwnershipHistoryIDTO ownershipHistoryIDTO) {
        Artifact artifact = artifactRepository.findArtifactsById(artifactId);
        if (artifact==null) throw new ApiException("Artifact not found");
        Record record = recordRepository.findRecordById(ownershipHistoryIDTO.getRecordId());
        if (record==null) throw new ApiException("Record not found");
        if (artifact.getRecord() != record) throw new ApiException("Record is not for this artifact");


        OwnershipHistory ownershipHistory = new OwnershipHistory();
        ownershipHistory.setOwner(ownershipHistoryIDTO.getOwner());
        ownershipHistory.setOwnershipPeriod(ownershipHistoryIDTO.getOwnershipPeriod());
        ownershipHistory.setRecord(artifact.getRecord());

        ownershipHistoryRepository.save(ownershipHistory);
    }

    public void addCertificate(Integer contributorId ,Integer artifactId, CertificateIDTO certificateIDTO) {
        Artifact artifact = artifactRepository.findArtifactsById(artifactId);
        if (artifact==null) throw new ApiException("Artifact not found");

        Contributor contributor = contributorRepository.findContributorById(contributorId);
        if (contributor==null) throw new ApiException("Contributor not found");

        if (artifact.getContributor()!=contributor) throw new ApiException("Contributor is not the owner of the artifact");

        Certificate certificate = new Certificate();

        certificate.setName(certificateIDTO.getName());
        certificate.setArtifact(artifact);
        certificate.setExpirationDate(certificateIDTO.getExpirationDate());
        certificate.setGivingDate(certificateIDTO.getGivingDate());
        certificate.setUrl(certificateIDTO.getUrl());
        certificate.setType(certificateIDTO.getType());
        certificate.setRegistrationNumber(certificateIDTO.getRegistrationNumber());

        certificateRepository.save(certificate);
    }

    public void requestArtifactCertification(Integer artifactId) {
        Artifact artifact = artifactRepository.findArtifactsById(artifactId);
        if (artifact==null) throw new ApiException("Artifact not found");
        if (!artifact.getCertificates().isEmpty()) throw new ApiException("Artifact already certified");
        if (artifact.getContributor().getIsBanned())
            throw new ApiException("Contributor is banned");
    }

    public List<Request> viewBorrowingRequests(Integer contributorId) {
        Contributor contributor = contributorRepository.findContributorById(contributorId);
        if (contributor==null) throw new ApiException("Contributor not found");
        if (contributor.getIsBanned()) throw new ApiException("Contributor is banned");

        List<Request> requests = requestRepository.findRequestsByContributor(contributor);
        if (requests==null) throw new ApiException("Requests not found");

        List<Request> borrowingRequests = requestRepository.findRequestsByDecisionAndContributor("pending", contributor);
        if (borrowingRequests==null) throw new ApiException("No borrowing requests found");


        return borrowingRequests;
    }

    public void decideOnBorrowRequest(Integer contributorId, Integer requestId, String decision) {
        Request request = requestRepository.findRequestById(requestId);
        if (request==null) throw new ApiException("Request not found");
        Contributor contributor = contributorRepository.findContributorById(contributorId);
        if (contributor==null) throw new ApiException("Contributor not found");
        if (contributor.getIsBanned()) throw new ApiException("Contributor is banned");

        if (!request.getContributor().getId().equals(contributorId)) throw new ApiException("Contributor is not the owner of the request");
        if (!request.getDecision().equals("pending")) throw new ApiException("Decision is already made");

        if (decision.equalsIgnoreCase("accept")) {
            request.setDecision("accepted");
        } else if (decision.equalsIgnoreCase("reject")) {
            request.setDecision("rejected");
        } else {
            throw new ApiException("Invalid decision. Use 'accept' or 'reject'");
        }
        requestRepository.save(request);
    }

    public void giveFeedbackOnBorrower(Integer contributorId, Integer requestId, FeedbackDTO feedbackDTO) {
        Contributor contributor = contributorRepository.findContributorById(contributorId);
        if (contributor==null) throw new ApiException("Contributor not found");
        if (contributor.getIsBanned()) throw new ApiException("Contributor is banned");

        Request request = requestRepository.findRequestById(requestId);
        if (request==null) throw new ApiException("Request not found");

        if (!request.getContributor().equals(contributor)) throw new ApiException("Contributor did not receive this request");

        if (request.getEndDate().isAfter(LocalDate.now())){
            throw new ApiException("can't give feedback until end date");
        }

        feedbackDTO.setSenderEmail(request.getContributor().getEmail());

        if (request.getResearcher() == null) feedbackDTO.setReceiverEmail(request.getOrganization().getEmail());
        if (request.getOrganization() == null) feedbackDTO.setReceiverEmail(request.getResearcher().getEmail());

        feedbackDTO.setCreatorType("contributor");
        feedbackDTO.setCreatorId(request.getContributor().getId());

        feedbackService.createFeedback(requestId, feedbackDTO);
    }

    public List<FeedbackODTO> viewReceivedFeedbacks(Integer id) {
        Contributor contributor = contributorRepository.findContributorById(id);
        if (contributor==null) throw new ApiException("Contributor not found");
        if (contributor.getIsBanned()) throw new ApiException("Contributor is banned");
        List<Feedback> feedbacks = feedbackRepository.findFeedbackByReceiverEmail(contributor.getEmail());
        if (feedbacks==null) throw new ApiException("No feedbacks found");

        return feedbackService.convertFeedBackToODTo(feedbacks);
    }

//Bayan
    public void report (Integer contributor_id, ReportIDTO reportIDTO){
        Contributor contributor = contributorRepository.findContributorById(contributor_id);
        if(contributor==null){
            throw new ApiException("contributor not found");
        }
        if (contributor.getIsBanned()) throw new ApiException("Contributor is banned");
        reportService.createReport(reportIDTO);
    }
//Bayan
    public List<ReportODTO> getReportsSentByContributor (Integer contributor_id){
        Contributor contributor =contributorRepository.findContributorById(contributor_id);
        if(contributor==null){
            throw new ApiException("contributor not found");
        }
        if (contributor.getIsBanned()) throw new ApiException("Contributor is banned");
        return reportService.convertReportToDTo(reportRepository.findAllBySender(contributor_id));
    }


}
