package com.zerobase.fastlms.history.repository;

import com.zerobase.fastlms.history.dto.LoginHistoryDto;
import com.zerobase.fastlms.history.entity.LoginHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LoginHisotryRepository extends JpaRepository<LoginHistory, Long> {

    @Query("select new com.zerobase.fastlms.history.dto.LoginHistoryDto(id, successDate, ip, userAgent) " +
            " from LoginHistory " +
            " where userId = :userId " +
            " order by id desc ")
    public List<LoginHistoryDto> findAllByUserIdOrderById(@Param("userId") String userId);

}
