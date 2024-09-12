package com.bitgo.crypto_notifier.notification.domain.service.mocks;

import org.springframework.stereotype.Service;

import com.bitgo.crypto_notifier.notification.domain.service.IEmailService;
import com.bitgo.crypto_notifier.notification.persistence.models.Notification;

@Service
public class MockEmailService implements IEmailService {
    @Override
    public void sendEmailNotification(Notification notification) {
        return;
    }
}
