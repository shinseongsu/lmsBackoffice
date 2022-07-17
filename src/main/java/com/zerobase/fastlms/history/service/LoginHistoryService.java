package com.zerobase.fastlms.history.service;

import com.zerobase.fastlms.history.dto.LoginHistoryDto;
import com.zerobase.fastlms.history.entity.LoginHistory;
import com.zerobase.fastlms.history.repository.LoginHisotryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoginHistoryService {

    private final LoginHisotryRepository loginHisotryRepository;

    @Transactional
    public void save(LoginHistory loginHistory) {
        loginHisotryRepository.save(loginHistory);
    }

    @Transactional(readOnly = true)
    public List<LoginHistoryDto> findAllByUserIdOrderById(String userId) {
        return loginHisotryRepository.findAllByUserIdOrderById(userId);
    }

}
