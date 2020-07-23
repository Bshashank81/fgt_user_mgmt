package com.sample.financialgoaltracker.repository;

import com.sample.financialgoaltracker.entity.Setting;
import org.springframework.data.repository.CrudRepository;

public interface SettingRepository extends CrudRepository<Setting,Integer> {
    Setting findByUserId(int userId);
}
