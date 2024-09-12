package com.bitgo.crypto_notifier.notification.persistence.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.bitgo.crypto_notifier.notification.persistence.models.NotificationTemplate;

public interface NotificationTemplateRepository extends JpaRepository<NotificationTemplate, UUID> {
}
