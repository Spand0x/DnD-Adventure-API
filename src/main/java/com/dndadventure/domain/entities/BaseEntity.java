package com.dndadventure.domain.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntity {
    private String uuid;

    public BaseEntity() {
    }

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(unique = true, nullable = false, insertable = false, updatable = false)
    public String getUuid() {
        return uuid;
    }

    public BaseEntity setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }
}
