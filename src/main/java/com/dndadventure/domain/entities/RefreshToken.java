package com.dndadventure.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "refresh_tokens")
public class RefreshToken extends BaseEntity {
    private String value;
    private LocalDateTime expiresAt;
    private User user;

    public RefreshToken() {
    }

    @Column(columnDefinition = "TEXT")
    public String getValue() {
        return value;
    }

    public RefreshToken setValue(String value) {
        this.value = value;
        return this;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public RefreshToken setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
        return this;
    }

    @OneToOne
    public User getUser() {
        return user;
    }

    public RefreshToken setUser(User user) {
        this.user = user;
        return this;
    }
}
