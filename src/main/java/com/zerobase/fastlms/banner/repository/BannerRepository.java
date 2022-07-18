package com.zerobase.fastlms.banner.repository;

import com.zerobase.fastlms.admin.dto.BannerDto;
import com.zerobase.fastlms.banner.entity.Banner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BannerRepository extends JpaRepository<Banner, Long> {

    @Query(value = "select new com.zerobase.fastlms.admin.dto.BannerDto(id, name, url, createDate) " +
            " from Banner ",
            countQuery = "select count(*) from Banner")
    public Page<BannerDto> findMainList(Pageable pageable);


    @Query(value = "select new com.zerobase.fastlms.admin.dto.BannerDto(id, name, url, createDate) " +
            " from Banner " +
            " where hide = false " +
            " order by orderNumber " )
    public List<BannerDto> findAllByMain();

}
