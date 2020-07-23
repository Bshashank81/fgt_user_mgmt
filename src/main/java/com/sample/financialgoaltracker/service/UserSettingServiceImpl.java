package com.sample.financialgoaltracker.service;

import com.sample.financialgoaltracker.dto.UserSettingDTO;
import com.sample.financialgoaltracker.entity.UserSetting;
import com.sample.financialgoaltracker.mapper.UserSettingMapper;
import com.sample.financialgoaltracker.repository.UserSettingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserSettingServiceImpl implements UserSettingService {
    @Autowired
    private UserSettingRepository userSettingRepository;

    @Autowired
    private UserSettingMapper userSettingMapper;

    @Override
    public List<UserSettingDTO> findAll(){
        List<UserSettingDTO> userSettingDTOS = new ArrayList<>();
        List<UserSetting> userSettings = (List<UserSetting>) userSettingRepository.findAll();

        for(UserSetting userSetting: userSettings){
            UserSettingDTO userSettingDTO = userSettingMapper.convertToDTO(userSetting);
            userSettingDTOS.add(userSettingDTO);
        }
        return userSettingDTOS;
    }

    @Override
    public UserSettingDTO findById(int id){
        return userSettingMapper.convertToDTO(userSettingRepository.findById(id).get());
    }

    @Override
    public UserSettingDTO save(UserSettingDTO theUserSettingDTO){
        UserSetting userSetting = userSettingMapper.convertToEntity(theUserSettingDTO);
        userSettingRepository.save(userSetting);
        UserSettingDTO userSettingDTO = userSettingMapper.convertToDTO(userSetting);
        return userSettingDTO;
    }

    @Override
    public boolean deleteById(int id) {
        userSettingRepository.deleteById(id);
        return true;
    }



}
