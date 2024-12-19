package com.example.capstone3.Controller;

import com.example.capstone3.Service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/tag")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;

    @GetMapping("/get")
    public ResponseEntity<?> getAllTags() {
        return ResponseEntity.status(200).body(tagService.getAllTags());
    }
}
