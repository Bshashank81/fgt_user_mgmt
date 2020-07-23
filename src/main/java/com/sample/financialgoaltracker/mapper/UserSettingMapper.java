package com.sample.financialgoaltracker.mapper;

import com.sample.financialgoaltracker.dto.UserSettingDTO;
import com.sample.financialgoaltracker.entity.UserSetting;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserSettingMapper {

    @Autowired
    private ModelMapper modelMapper;

    public UserSetting convertToEntity(UserSettingDTO userSettingDTO){
        return modelMapper.map(userSettingDTO, UserSetting.class);
    }

    public UserSettingDTO convertToDTO(UserSetting userSetting){
        return modelMapper.map(userSetting,UserSettingDTO.class);
    }
}
