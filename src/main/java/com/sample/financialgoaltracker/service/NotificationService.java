package com.sample.financialgoaltracker.service;

import com.sample.financialgoaltracker.dto.NotificationDTO;
import com.sample.financialgoaltracker.dto.UserSettingDTO;

import java.util.List;

public interface NotificationService {
    List<NotificationDTO> findAll();

    NotificationDTO findById(int id);

    NotificationDTO save(NotificationDTO notificationDTO);


    void delete(NotificationDTO notificationDTO);

    NotificationDTO findByUserId(int id);

    boolean deleteById(int id);

}
