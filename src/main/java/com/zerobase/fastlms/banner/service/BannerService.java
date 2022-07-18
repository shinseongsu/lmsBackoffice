package com.zerobase.fastlms.banner.service;

import com.zerobase.fastlms.admin.dto.BannerDto;
import com.zerobase.fastlms.admin.dto.BannerRequestDto;
import com.zerobase.fastlms.banner.constant.OpenType;
import com.zerobase.fastlms.banner.entity.Banner;
import com.zerobase.fastlms.banner.repository.BannerRepository;
import com.zerobase.fastlms.util.file.FileService;
import com.zerobase.fastlms.util.file.UploadFile;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.io.IOException;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class BannerService {

    private final FileService fileService;
    private final BannerRepository bannerRepository;

    @Transactional(readOnly = true)
    public Page<BannerDto> findAllByOrderByIdAsc(Pageable pageable) {
        return bannerRepository.findMainList(pageable);
    }

    @Transactional(readOnly = true)
    public List<BannerDto> findAllByMainBanner() {
        return bannerRepository.findAllByMain();
    }

    public void saveBanner(BannerRequestDto bannerRequestDto) throws IOException {
        // 파일 저장
        UploadFile uploadFile = new UploadFile();

        if (bannerRequestDto.getOpenType().equals(OpenType.FILE)) {
            uploadFile = fileService.storeFile(bannerRequestDto.getFile());
        }

        bannerRepository.save(bannerRequestDto.toEntity(uploadFile));
    }

    @Transactional(readOnly = true)
    public BannerRequestDto findById(Long id) {
        Banner banner = bannerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("배너에 없습니다."));

        return BannerRequestDto.of(banner);
    }

    public void update(@ModelAttribute BannerRequestDto bannerRequestDto) {
        try {
            Banner originalBanner = bannerRepository.findById(bannerRequestDto.getId())
                    .orElseThrow(() -> new IllegalArgumentException("배너에 없습니다."));

            // 파일 일때 수정
            if (!bannerRequestDto.getFile().isEmpty()
                    && originalBanner.getType().equals(OpenType.FILE)) {
                UploadFile uploadFile = fileService.storeFile(bannerRequestDto.getFile());
                originalBanner.updateUrl(uploadFile.getStoreFileName());
            }

            // url 일 때 수정
            if ( originalBanner.getType().equals(OpenType.LINK)
                    && bannerRequestDto.getUrl() != null) {
                originalBanner.updateUrl(bannerRequestDto.getUrl());
            }

            originalBanner.update(bannerRequestDto.getName(),
                    bannerRequestDto.getOrderNumber(),
                    !bannerRequestDto.isHide());

        } catch (Exception e) {
            log.error("{} ", e.getMessage());
        }
    }

    @Transactional
    public void delete(Map<String, Object> params) {
        List<String> list = (ArrayList<String>) params.get("ids");

        list.stream().forEach(x -> bannerRepository.deleteById(Long.valueOf(x)));

    }

}
