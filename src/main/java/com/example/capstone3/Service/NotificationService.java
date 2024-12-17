package com.example.capstone3.Service;

import com.example.capstone3.Model.Notification;
import com.example.capstone3.Repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;

    public void addNotification(Notification notification) {
        notificationRepository.save(notification);
    }



}
