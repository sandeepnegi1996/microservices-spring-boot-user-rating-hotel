package com.lcwd.user.service.UserService.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users",
        schema = "user-db" ,
        uniqueConstraints = {
            @UniqueConstraint(name = "uk_users_id",columnNames = "ID")
        },
        indexes = {
            @Index(name = "idx_users_id",columnList = "ID")
        })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @Column(name = "ID")
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private String userId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "ABOUT")
    private String about;

    @Column(name = "CREATED_AT",nullable = false, updatable = false)
    private Instant createdAt;

    @Column(name = "UPDATED_AT",nullable = false )
    private Instant updatedAt;

    //optimistic locking
    @Version
    private Long version;

    // lifecycle call backs
    @PrePersist
    protected void onCreate() {
        Instant now = Instant.now();
        this.createdAt= now;
        this.updatedAt = now;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = Instant.now();
    }

    @Transient
    private List<Rating> rating = new ArrayList<>();


}
