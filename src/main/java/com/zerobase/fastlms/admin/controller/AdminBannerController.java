package com.zerobase.fastlms.admin.controller;

import com.zerobase.fastlms.admin.dto.BannerDto;
import com.zerobase.fastlms.admin.dto.BannerRequestDto;
import com.zerobase.fastlms.banner.entity.Banner;
import com.zerobase.fastlms.banner.service.BannerService;
import com.zerobase.fastlms.util.PageUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/banner")
@Slf4j
public class AdminBannerController {
    private final BannerService bannerService;

    @GetMapping(value = "/main.do")
    public String main(Model model, Pageable pageable) {
        Page<BannerDto> bannerList = bannerService.findAllByOrderByIdAsc(pageable);

        long totalCount = bannerList.getContent().size();
        int pageIndex = pageable.getPageNumber();
        String pagerHtml = getPaperHtml(totalCount, pageable.getPageSize(), pageIndex);

        model.addAttribute("bannerList", bannerList);
        model.addAttribute("pager", pagerHtml);
        model.addAttribute("totalCount", totalCount);

        return "admin/banner/main";
    }

    @GetMapping("/add.do")
    public String add(Model model) {

        BannerRequestDto bannerRequestDto = new BannerRequestDto();

        model.addAttribute("bannerInfo", bannerRequestDto);

        return "admin/banner/add";
    }

    @PostMapping("/insert.do")
    public String insert(@ModelAttribute BannerRequestDto bannerRequestDto) {
        try {
            bannerService.saveBanner(bannerRequestDto);
        } catch (Exception e) {
            log.error("업로드에 실패하였습니다. {} ", e.getMessage());
        }

        return "redirect:/admin/banner/main.do";
    }

    @GetMapping("/detail.do")
    public String detail(@RequestParam Long id,  Model model) {
        model.addAttribute("bannerInfo", bannerService.findById(id));
        return "/admin/banner/detail";
    }

    @PostMapping("/update.do")
    public String update(@ModelAttribute BannerRequestDto bannerRequestDto, Model model) {
        bannerService.update(bannerRequestDto);

        return "redirect:/admin/banner/main.do";
    }

    @PostMapping("/delete.do")
    public ResponseEntity<?> delete(@RequestBody Map<String, Object> request, Model model) {

        bannerService.delete(request);

        return ResponseEntity.ok().body("");
    }


    public String getPaperHtml(long totalCount, long pageSize, long pageIndex) {
        PageUtil pageUtil = new PageUtil(totalCount, pageSize, pageIndex, "");
        return pageUtil.pager();
    }

}
