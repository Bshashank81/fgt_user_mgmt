package com.sample.financialgoaltracker.service;

import com.sample.financialgoaltracker.dto.SettingDTO;

import java.util.List;

public interface SettingService {
    List<SettingDTO> findAll();

    SettingDTO findById(int id);

    SettingDTO save(SettingDTO settingDTO);

    void delete(SettingDTO settingDTO);

    SettingDTO findByUser(int userId);

    boolean deleteById(int id);

}
