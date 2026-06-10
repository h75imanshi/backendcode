package com.eoffice.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "login_activity")
@Data
@NoArgsConstructor
public class LoginActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false, length = 50)
    private String username;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "login_time", nullable = false)
    private LocalDateTime loginTime;

    @Column(name = "ip_address", length = 50)
    private String ipAddress;

    @Column(name = "login_status", length = 20)
    private String loginStatus; // SUCCESS / FAILED

    @Column(name = "user_agent", length = 255)
    private String userAgent;
    @Column(name = "token", length = 500)
    private String token;

    @PrePersist
    protected void onCreate() {
        loginTime = LocalDateTime.now();
    }

    public void setLoginTime(long l) {

    }
}
