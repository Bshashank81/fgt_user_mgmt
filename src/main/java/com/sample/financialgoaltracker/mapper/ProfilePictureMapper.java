package com.sample.financialgoaltracker.mapper;

import com.sample.financialgoaltracker.dto.ProfilePictureDTO;
import com.sample.financialgoaltracker.entity.ProfilePicture;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProfilePictureMapper {

    @Autowired
    private ModelMapper modelMapper;

    public ProfilePicture convertToEntity(ProfilePictureDTO profilePictureDTO) {

        return modelMapper.map(profilePictureDTO, ProfilePicture.class);
    }

    public ProfilePictureDTO convertToDTO(ProfilePicture profilePicture) {

        return modelMapper.map(profilePicture, ProfilePictureDTO.class);
    }


}
