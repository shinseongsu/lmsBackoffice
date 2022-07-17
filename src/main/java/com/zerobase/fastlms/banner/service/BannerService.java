package com.zerobase.fastlms.banner.service;

import com.zerobase.fastlms.banner.entity.Banner;
import com.zerobase.fastlms.banner.repository.BannerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BannerService {

    private final BannerRepository bannerRepository;

    public List<Banner> findByAll() {
        return bannerRepository.findAll();
    }

}
