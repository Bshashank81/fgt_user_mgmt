package com.sample.financialgoaltracker.mapper;

import com.sample.financialgoaltracker.dto.NotificationDTO;
import com.sample.financialgoaltracker.dto.SettingDTO;
import com.sample.financialgoaltracker.entity.Notification;
import com.sample.financialgoaltracker.entity.Setting;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NotificationMapper {

    @Autowired
    private ModelMapper modelMapper;

    public Notification convertToEntity(NotificationDTO notificationDTO){
        return modelMapper.map(notificationDTO, Notification.class);
    }

    public NotificationDTO convertToDTO(Notification notification){
        return modelMapper.map(notification, NotificationDTO.class);
    }
}
