package com.bitgo.crypto_notifier.notification.persistence.models;

import java.time.ZonedDateTime;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "notifications")
@Getter
@Setter
public class Notification {
    @Id
    private UUID id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "template_id", referencedColumnName = "id")
    private NotificationTemplate template;
    private String email;
    private String dataNotified;
    private ZonedDateTime timestamp;
}
