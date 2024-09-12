package com.bitgo.crypto_notifier.notification.domain.service;

import com.bitgo.crypto_notifier.notification.persistence.models.Notification;

public interface IEmailService {
    void sendEmailNotification(Notification notification);
}
