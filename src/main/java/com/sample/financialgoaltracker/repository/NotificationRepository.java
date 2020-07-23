package com.sample.financialgoaltracker.repository;

import com.sample.financialgoaltracker.entity.Notification;
import org.springframework.data.repository.CrudRepository;

public interface NotificationRepository extends CrudRepository<Notification, Integer> {
    Notification findByUserId(int id);
}
