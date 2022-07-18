package com.zerobase.fastlms.admin.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class BannerDto {

    private Long no;
    private String name;
    private String url;
    private LocalDateTime createDate;

    public BannerDto(Long no, String name, String url, LocalDateTime createDate) {
        this.no = no;
        this.name = name;
        this.url = url.startsWith("http") ? url : "/images/" + url;
        this.createDate = createDate;
    }

}
