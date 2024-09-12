package com.bitgo.crypto_notifier.notification.persistence.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.bitgo.crypto_notifier.notification.persistence.models.Notification;

public interface NotificationRepository extends JpaRepository<Notification, UUID> {
    @Modifying
    @Query(value = "delete from notifications where template_id = :templateId", nativeQuery = true)
    int deleteNotificationsOfSomeTemplate(String templateId);
}
