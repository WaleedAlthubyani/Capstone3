package com.example.capstone3.Controller;

import com.example.capstone3.API.ApiResponse;
import com.example.capstone3.Model.Event;
import com.example.capstone3.Service.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/event")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;


    @GetMapping("/get")
    public ResponseEntity get (){
        return ResponseEntity.status(200).body(eventService.getAll());
    }

//    @PostMapping("/add/{organization_id}")
//    public ResponseEntity add (@PathVariable  Integer organization_id , @RequestBody @Valid Event event){
//        eventService.addEvent(organization_id,event);
//        return ResponseEntity.status(200).body(new ApiResponse("Event added successfully"));
//    }
//    @PostMapping("/add")
//    public ResponseEntity add (@RequestBody @Valid Event event){
//        eventService.add(event);
//        return ResponseEntity.status(200).body(new ApiResponse("Event added successfully"));
//    }

    @PutMapping("/update/{id}")
    public ResponseEntity update (@PathVariable Integer id , @RequestBody @Valid Event event){
        eventService.update(id,event);
        return ResponseEntity.status(200).body(new ApiResponse("Event update successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete (@PathVariable Integer id){
        eventService.delete(id);
        return ResponseEntity.status(200).body(new ApiResponse("Event deleted successfully"));
    }
}
