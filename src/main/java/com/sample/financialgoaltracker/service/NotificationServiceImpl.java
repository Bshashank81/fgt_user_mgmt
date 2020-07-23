package com.sample.financialgoaltracker.service;

import com.sample.financialgoaltracker.dto.NotificationDTO;
import com.sample.financialgoaltracker.entity.Message;

import com.sample.financialgoaltracker.entity.Notification;
import com.sample.financialgoaltracker.mapper.NotificationMapper;
import com.sample.financialgoaltracker.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private NotificationMapper notificationMapper;

    @Override
    public List<NotificationDTO> findAll(){
        List<NotificationDTO> notificationDTOS = new ArrayList<>();
        List<Notification> notifications = (List<Notification>) notificationRepository.findAll();

        for(Notification notification: notifications){
            NotificationDTO notificationDTO = notificationMapper.convertToDTO(notification);
            notificationDTOS.add(notificationDTO);
        }
        return notificationDTOS;
    }

    @Override
    public NotificationDTO findById(int id){
        return notificationMapper.convertToDTO(notificationRepository.findById(id).get());
    }

    @Override
    public NotificationDTO save(NotificationDTO theNotificationDTO){
        Notification notification = notificationMapper.convertToEntity(theNotificationDTO);
        notificationRepository.save(notification);
        NotificationDTO notificationDTO = notificationMapper.convertToDTO(notification);
        return notificationDTO;
    }

    @Override
    public void delete(NotificationDTO notificationDTO){
        notificationRepository.delete(notificationMapper.convertToEntity(notificationDTO));
    }

    @Override
    public NotificationDTO findByUserId(int id) {
        Notification notification= notificationRepository.findByUserId(id);
        if(notification==null){
            return null;
        }
        else {
            return notificationMapper.convertToDTO(notification);
        }
    }

    public boolean deleteById(int id) {
        notificationRepository.deleteById(id);
        return true;
    }




}
