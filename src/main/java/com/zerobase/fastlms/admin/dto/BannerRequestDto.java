package com.zerobase.fastlms.admin.dto;

import com.zerobase.fastlms.banner.constant.OpenType;
import com.zerobase.fastlms.banner.entity.Banner;
import com.zerobase.fastlms.util.file.UploadFile;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class BannerRequestDto {
    private Long id;
    private String name;
    private MultipartFile file;
    private String url;
    private OpenType openType;
    private Integer orderNumber;
    private boolean hide;
    private boolean chk;

    public Banner toEntity(UploadFile uploadFile) {
        return Banner.builder()
                .name(this.name)
                .url( this.openType.equals(OpenType.FILE) ? uploadFile.getStoreFileName() : this.url)
                .type(this.openType)
                .orderNumber(this.orderNumber)
                .hide(!this.hide)
                .createDate(LocalDateTime.now())
                .build();
    }

    public static BannerRequestDto of(Banner banner) {
        return BannerRequestDto.builder()
                .id(banner.getId())
                .name(banner.getName())
                .url(banner.getUrl())
                .openType(banner.getType())
                .orderNumber(banner.getOrderNumber())
                .hide(banner.isHide())
                .build();
    }

}
