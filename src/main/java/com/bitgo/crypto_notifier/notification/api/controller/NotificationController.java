package com.bitgo.crypto_notifier.notification.api.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bitgo.crypto_notifier.notification.api.models.NotificationSendRequest;
import com.bitgo.crypto_notifier.notification.domain.service.NotificationService;
import com.bitgo.crypto_notifier.notification.persistence.models.Notification;
import com.bitgo.crypto_notifier.notification.persistence.models.NotificationTemplate;
import com.bitgo.crypto_notifier.notification.persistence.repository.NotificationRepository;
import com.bitgo.crypto_notifier.notification.persistence.repository.NotificationTemplateRepository;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    // TODO remove dependency on repositories
    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private NotificationTemplateRepository templateRepository;

    @PostMapping("/templates")
    public ResponseEntity<NotificationTemplate> createNotificationTemplate(@RequestBody NotificationTemplate request) {
        // TODO validate and sanitise request
        request.setId(UUID.randomUUID());
        NotificationTemplate createdTemplate = templateRepository.save(request);
        return ResponseEntity.ok().body(createdTemplate);
    }

    @PostMapping("/templates/{templateId}/send")
    public ResponseEntity<String> sendNotification(@PathVariable UUID templateId,
            @RequestBody NotificationSendRequest request) {
        String email = request.getEmail();
        // TODO error handling
        NotificationTemplate template = templateRepository.findById(templateId)
                .orElseThrow(() -> new IllegalArgumentException("No such template"));
        notificationService.sendNotification(email, template);
        return ResponseEntity.ok().body("Sent");
    }

    @GetMapping
    public ResponseEntity<List<Notification>> listSentNotifications() {
        return ResponseEntity.ok().body(notificationRepository.findAll());
    }

    @DeleteMapping("/templates/{templateId}")
    public ResponseEntity<String> deleteTemplate(@PathVariable UUID templateId) {
        // TODO error handling
        NotificationTemplate template = templateRepository.findById(templateId)
                .orElseThrow(() -> new IllegalArgumentException("No such template"));
        notificationService.deleteTemplate(template);
        return ResponseEntity.ok().body("Deleted");
    }
}
