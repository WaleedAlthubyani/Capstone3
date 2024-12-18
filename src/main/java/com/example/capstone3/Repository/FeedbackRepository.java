package com.example.capstone3.Repository;

import com.example.capstone3.Model.Feedback;
import com.example.capstone3.Model.Researcher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback,Integer> {

    Feedback findFeedbackById (Integer id);

    List<Feedback> findFeedbackByReceiverId(Integer id);

@Query("select f from Feedback f where f.organization.id=?1")
List<Feedback> findFeedbackByOrganizationId (Integer organization_id);
}
