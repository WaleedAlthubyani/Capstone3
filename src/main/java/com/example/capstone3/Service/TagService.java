package com.example.capstone3.Service;

import com.example.capstone3.DTO.TagDTO;
import com.example.capstone3.Model.Tag;
import com.example.capstone3.Repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TagService {

    private final TagRepository tagRepository;

    public List<TagDTO> getAllTags() {
        return convertTagToDTO(tagRepository.findAll());
    }

    public List<TagDTO> convertTagToDTO(List<Tag> tags) {
        List<TagDTO> tagDTOs = new ArrayList<>();
        for (Tag tag : tags) {
            tagDTOs.add(new TagDTO(tag.getName(),tag.getDescription()));
        }
        return tagDTOs;
    }


}
