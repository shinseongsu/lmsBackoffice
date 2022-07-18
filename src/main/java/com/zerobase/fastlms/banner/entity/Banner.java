package com.zerobase.fastlms.banner.entity;

import com.zerobase.fastlms.admin.dto.BannerRequestDto;
import com.zerobase.fastlms.banner.constant.OpenType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Banner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String url;

    @Enumerated(EnumType.STRING)
    private OpenType type;

    private Integer orderNumber;
    private boolean hide;

    private LocalDateTime createDate;

    @Builder
    public Banner(String name, String url, OpenType type, Integer orderNumber, boolean hide, LocalDateTime createDate) {
        this.name = name;
        this.url = url;
        this.type = type;
        this.orderNumber = orderNumber;
        this.hide = hide;
        this.createDate = createDate;
    }

    public void updateUrl(String url) {
        this.url = url;
    }

    public void update(String name, Integer orderNumber, boolean hide) {
        if(name != null) this.name = name;
        if(orderNumber != null) this.orderNumber = orderNumber;
        this.hide = hide;
    }

}
