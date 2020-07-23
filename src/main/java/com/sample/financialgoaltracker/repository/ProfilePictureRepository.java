package com.sample.financialgoaltracker.repository;

import com.sample.financialgoaltracker.entity.Message;
import com.sample.financialgoaltracker.entity.ProfilePicture;
import org.springframework.data.repository.CrudRepository;

public interface ProfilePictureRepository extends CrudRepository<ProfilePicture, Integer> {

}
