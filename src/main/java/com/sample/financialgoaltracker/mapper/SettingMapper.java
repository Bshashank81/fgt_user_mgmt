package com.sample.financialgoaltracker.mapper;

import com.sample.financialgoaltracker.dto.SettingDTO;
import com.sample.financialgoaltracker.entity.Setting;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SettingMapper {

    @Autowired
    private ModelMapper modelMapper;

    public Setting convertToEntity(SettingDTO settingDTO){
        return modelMapper.map(settingDTO, Setting.class);
    }

    public SettingDTO convertToDTO(Setting setting){
        return modelMapper.map(setting, SettingDTO.class);
    }
}
