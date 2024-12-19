package com.example.capstone3.Repository;

import com.example.capstone3.Model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
//Waleed
@Repository
public interface TagRepository extends JpaRepository<Tag, Integer> {
    Tag findTagById(Integer id);
}
