package com.zerobase.fastlms.history.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "login_history")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class LoginHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;
    private LocalDateTime successDate;
    private String ip;
    private String userAgent;

    @Builder
    public LoginHistory(String userId, LocalDateTime successDate, String ip, String userAgent) {
        this.userId = userId;
        this.successDate = successDate;
        this.ip = ip;
        this.userAgent = userAgent;
    }

}
