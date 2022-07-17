package com.zerobase.fastlms.banner.entity;

import com.zerobase.fastlms.banner.constant.OpenType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Banner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String fileUrl;
    private String linkUrl;

    @Enumerated(EnumType.STRING)
    private OpenType type;

    private Integer order;
    private boolean hide;

}
