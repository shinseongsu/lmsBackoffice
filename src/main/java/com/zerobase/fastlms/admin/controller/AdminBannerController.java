package com.zerobase.fastlms.admin.controller;

import com.zerobase.fastlms.banner.entity.Banner;
import com.zerobase.fastlms.banner.service.BannerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminBannerController {
    private final BannerService bannerService;

    @GetMapping("admin/banner/main.do")
    public String main(Model model) {
        List<Banner> bannerList = bannerService.findByAll();
        model.addAttribute("bannerList", bannerList);
        
        return "admin/banner/main";
    }

}
