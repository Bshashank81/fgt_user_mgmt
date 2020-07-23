package com.sample.financialgoaltracker.service;

import com.sample.financialgoaltracker.dto.SettingDTO;
import com.sample.financialgoaltracker.entity.Setting;
import com.sample.financialgoaltracker.mapper.SettingMapper;
import com.sample.financialgoaltracker.repository.SettingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SettingServiceImpl implements SettingService {
    @Autowired
    private SettingRepository settingRepository;

    @Autowired
    private SettingMapper settingMapper;

    @Override
    public List<SettingDTO> findAll(){
        List<SettingDTO> settingDTOS = new ArrayList<>();
        List<Setting> settings = (List<Setting>) settingRepository.findAll();

        for(Setting setting: settings){
            SettingDTO settingDTO = settingMapper.convertToDTO(setting);
            settingDTOS.add(settingDTO);
        }
        return settingDTOS;
    }

    @Override
    public SettingDTO findById(int id){
        return settingMapper.convertToDTO(settingRepository.findById(id).get());
    }

    @Override
    public SettingDTO save(SettingDTO theSettingDTO){
        Setting setting = settingMapper.convertToEntity(theSettingDTO);
        settingRepository.save(setting);
        SettingDTO settingDTO = settingMapper.convertToDTO(setting);
        return settingDTO;
    }

    @Override

    public void delete(SettingDTO settingDTO){
        settingRepository.delete(settingMapper.convertToEntity(settingDTO));
    }

    @Override
    public SettingDTO findByUser(int userId) {
        Setting setting= settingRepository.findByUserId(userId);
        if(setting==null){
            return null;
        }
        else {
            return settingMapper.convertToDTO(setting);
        }
    }

    public boolean deleteById(int id) {
        settingRepository.deleteById(id);
        return true;
    }




}
