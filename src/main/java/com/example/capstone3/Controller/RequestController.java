package com.example.capstone3.Controller;

import com.example.capstone3.Service.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class RequestController {

    private final RequestService requestService;


}
