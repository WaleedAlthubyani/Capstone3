package com.example.capstone3.Service;

import com.example.capstone3.Api.ApiException;
import com.example.capstone3.DTO.ImageDTO;
import com.example.capstone3.Model.Artifact;
import com.example.capstone3.Model.Image;
import com.example.capstone3.Repository.ArtifactRepository;
import com.example.capstone3.Repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;
    private final ArtifactRepository artifactRepository;

    public void addImage(ImageDTO imageDTO) {
        Artifact artifact = artifactRepository.findArtifactsById(imageDTO.getArtifactId());
        if (artifact == null) {
            throw new ApiException("Artifact not found");
        }

        Image image = new Image();
        image.setUrl(imageDTO.getUrl());
        image.setDescription(imageDTO.getDescription());
        image.setArtifact(artifact);
        imageRepository.save(image);
    }

    public void updateImage(Integer id,ImageDTO imageDTO) {
        Image image = imageRepository.findImageById(id);

        if (image == null) {
            throw new ApiException("Image not found");
        }

        Artifact artifact = artifactRepository.findArtifactsById(id);

        if (artifact == null) {
            throw new ApiException("Artifact not found");
        }

        image.setUrl(imageDTO.getUrl());
        image.setDescription(imageDTO.getDescription());
        image.setArtifact(artifact);

        imageRepository.save(image);
    }

    public void deleteImage(Integer id) {
        Image image = imageRepository.findImageById(id);

        if (image == null) {
            throw new ApiException("Image not found");
        }

        imageRepository.delete(image);
    }
}
