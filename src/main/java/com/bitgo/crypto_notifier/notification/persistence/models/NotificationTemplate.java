package com.bitgo.crypto_notifier.notification.persistence.models;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "notification_templates")
@Getter
@Setter
public class NotificationTemplate {
    @Id
    private UUID id;
    private String dataPoints;
    private String emailTemplate;
}
