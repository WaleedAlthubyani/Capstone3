package com.example.capstone3.Repository;

import com.example.capstone3.Model.BanList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BanListRepository extends JpaRepository<BanList,Integer> {

    BanList findBanListById(int id);

}
