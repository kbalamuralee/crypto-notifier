package com.bitgo.crypto_notifier.notification.domain.service;

import java.time.ZonedDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitgo.crypto_notifier.notification.persistence.models.Notification;
import com.bitgo.crypto_notifier.notification.persistence.models.NotificationTemplate;
import com.bitgo.crypto_notifier.notification.persistence.repository.NotificationRepository;
import com.bitgo.crypto_notifier.notification.persistence.repository.NotificationTemplateRepository;

import jakarta.transaction.Transactional;

@Service
public class NotificationService {
    @Autowired
    private IBtcService btcService;
    @Autowired
    private IEmailService emailService;
    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private NotificationTemplateRepository templateRepository;

    public void sendNotification(String email, NotificationTemplate template) {
        String dataPointKeys = template.getDataPoints();
        String btcData = btcService.getBtcData(dataPointKeys);
        Notification notification = new Notification();
        notification.setId(UUID.randomUUID());
        notification.setTemplate(template);
        notification.setEmail(email);
        notification.setDataNotified(btcData);
        notification.setTimestamp(ZonedDateTime.now());
        emailService.sendEmailNotification(notification);
        notificationRepository.save(notification);
    }

    @Transactional
    public void deleteTemplate(NotificationTemplate template) {
        UUID templateId = template.getId();
        notificationRepository.deleteNotificationsOfSomeTemplate(templateId.toString());
        templateRepository.delete(template);
    }
}
