package com.example.capstone3.Service;

import com.example.capstone3.API.ApiException;
import com.example.capstone3.DTO.*;
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
    private final FeedbackRepository feedbackRepository;

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




    //Mshari
    public void addArtifact(ArtifactIDTO artifactIDTO) {

        if (categoryRepository.findCategoryById(artifactIDTO.getCategoryId())==null) throw new ApiException("Category not found");

        Artifact artifact = new Artifact();

        artifact.setName(artifactIDTO.getName());
        artifact.setType(artifactIDTO.getType());
        artifact.setOrigin(artifactIDTO.getOrigin());
        artifact.setEra(artifactIDTO.getEra());
        artifact.setLocation(artifactIDTO.getLocation());
        artifact.setCondition(artifactIDTO.getCondition());
        artifact.setCategory(categoryRepository.findCategoryById(artifactIDTO.getCategoryId()));
        artifact.setContributor(contributorRepository.findContributorById(artifactIDTO.getContributorId()));
        artifact.setCategory(categoryRepository.findCategoryById(artifactIDTO.getCategoryId()));

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

//    public void addImage(Integer artifactId, MultipartFile file) {
//        Artifact artifact = artifactRepository.findArtifactsById(artifactId);
//        if (artifact==null) throw new ApiException("Artifact not found");
//
//        String imagePath = "uploads/" + file.getOriginalFilename();
//        artifact.getImages().add(new Image(null,imagePath,null,artifact));
//        artifactRepository.save(artifact);
//    }

    public void addImage(Integer artifactId, String url, String description) {
        Artifact artifact = artifactRepository.findArtifactsById(artifactId);
        if (artifact==null) throw new ApiException("Artifact not found");

        artifact.getImages().add(new Image(null, url,description,artifact));
        artifactRepository.save(artifact);
    }

    public void addOwnershipHistory(Integer artifactId, OwnershipHistoryIDTO ownershipHistoryIDTO) {
        Artifact artifact = artifactRepository.findArtifactsById(artifactId);
        if (artifact==null) throw new ApiException("Artifact not found");

        OwnershipHistory ownershipHistory = new OwnershipHistory();
        ownershipHistory.setOwner(ownershipHistoryIDTO.getOwner());
        ownershipHistory.setOwnershipPeriod(ownershipHistoryIDTO.getOwnershipPeriod());
        ownershipHistory.setRecord(artifact.getRecord());

        ownershipHistoryRepository.save(ownershipHistory);
    }

    public void addCertificate(Integer artifactId, CertificateIDTO certificateIDTO) {
        Artifact artifact = artifactRepository.findArtifactsById(artifactId);
        if (artifact==null) throw new ApiException("Artifact not found");

        Certificate certificate = new Certificate();
        certificate.setName(certificateIDTO.getName());
        certificate.setArtifact(artifact);

        certificateRepository.save(certificate);
    }

    public void requestArtifactCertification(Integer artifactId) {
        Artifact artifact = artifactRepository.findArtifactsById(artifactId);
        if (artifact==null) throw new ApiException("Artifact not found");
        if (artifact.getCertificates()!= null) throw new ApiException("Artifact already certified");
    }

    public List<Request> viewBorrowingRequests(Integer contributorId) {
        Contributor contributor = contributorRepository.findContributorById(contributorId);
        if (contributor==null) throw new ApiException("Contributor not found");

        List<Request> requests = requestRepository.findRequestsByContributor(contributor);
        if (requests==null) throw new ApiException("Requests not found");

        List<Request> borrowingRequests = requestRepository.findRequestsByDecisionAndContributor("pending", contributor);
        if (borrowingRequests==null) throw new ApiException("No borrowing requests found");


        return borrowingRequests;
    }

    public void decideOnBorrowRequest(Integer requestId, String decision) {
        Request Request = requestRepository.findRequestById(requestId);
        if (Request==null) throw new ApiException("Request not found");

        if (decision.equalsIgnoreCase("accept")) {
            Request.setDecision("accepted");
        } else if (decision.equalsIgnoreCase("reject")) {
            Request.setDecision("rejected");
        } else {
            throw new ApiException("Invalid decision. Use 'approve' or 'reject'");
        }
        requestRepository.save(Request);
    }

//    public void giveFeedbackOnBorrower(Integer requestId, FeedbackIDTO feedbackIDTO) {
//        Request borrowRequest = requestRepository.findRequestById(requestId);
//        if (borrowRequest==null) throw new ApiException("Request not found");
//
//        Feedback feedback = new Feedback();
//        feedback.setComment(feedbackIDTO.getComment());
//        feedback.setRating(feedbackIDTO.getRating());
//
//
//
//        feedbackRepository.save(feedback);
//    }
//
//    public List<Feedback> viewReceivedFeedbacks(Integer id) {
//
//    }
}
