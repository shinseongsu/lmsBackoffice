package com.zerobase.fastlms.history.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class LoginHistoryDto {

    private Long no;
    private LocalDateTime successDate;
    private String ip;
    private String userAgent;

    public LoginHistoryDto(Long no, LocalDateTime successDate, String ip, String userAgent) {
        this.no = no;
        this.successDate = successDate;
        this.ip = ip;
        this.userAgent = userAgent;
    }

}
