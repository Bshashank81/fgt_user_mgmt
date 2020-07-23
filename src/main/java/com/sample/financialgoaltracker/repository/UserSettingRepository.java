package com.sample.financialgoaltracker.repository;

import com.sample.financialgoaltracker.entity.UserSetting;
import org.springframework.data.repository.CrudRepository;

public interface UserSettingRepository extends CrudRepository<UserSetting, Integer> {
}
