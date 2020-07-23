package com.sample.financialgoaltracker.service;

import com.sample.financialgoaltracker.dto.ProfilePictureDTO;

import java.util.List;

public interface ProfilePictureService {
    List<ProfilePictureDTO> findAll();

    ProfilePictureDTO findById(int id);

    ProfilePictureDTO save(ProfilePictureDTO profilePictureDTO);
    void delete(ProfilePictureDTO profilePictureDTO);
    boolean deleteById(int id);

}
