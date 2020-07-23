package com.sample.financialgoaltracker.service;

import com.sample.financialgoaltracker.dto.UserSettingDTO;

import java.util.List;

public interface UserSettingService {
    List<UserSettingDTO> findAll();

    UserSettingDTO findById(int id);

    UserSettingDTO save(UserSettingDTO userSettingDTO);

    boolean deleteById(int id);
}
