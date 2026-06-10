package com.eoffice.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false, unique = true, length = 50)
    private String username;

    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    // BCrypt encrypted password stored here
    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @Column(name = "full_name", length = 100)
    private String fullName;

    @Column(name = "is_active")
    private Boolean isActive = true;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

   @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
       updatedAt = LocalDateTime.now();
   }

   @PreUpdate
    protected void onUpdate() {

       this.updatedAt = LocalDateTime.now();
   }

    public Object getRole() {
       return "ROLE_USER";
    }
}
