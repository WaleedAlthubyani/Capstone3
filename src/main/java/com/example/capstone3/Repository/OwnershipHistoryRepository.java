package com.example.capstone3.Repository;

import com.example.capstone3.Model.OwnershipHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
//Mshari
@Repository
public interface OwnershipHistoryRepository extends JpaRepository<OwnershipHistory, Integer> {

    OwnershipHistory findOwnershipHistoriesById(Integer id);
}
