package com.sample.financialgoaltracker.service;

import com.sample.financialgoaltracker.dto.ProfilePictureDTO;
import com.sample.financialgoaltracker.entity.ProfilePicture;
import com.sample.financialgoaltracker.mapper.ProfilePictureMapper;
import com.sample.financialgoaltracker.repository.ProfilePictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProfilePictureServiceImpl implements ProfilePictureService {
    @Autowired
    private ProfilePictureRepository profilePictureRepository;

    @Autowired
    private ProfilePictureMapper profilePictureMapper;

    @Override
    public List<ProfilePictureDTO> findAll(){
        List<ProfilePictureDTO> profilePictureDTOS = new ArrayList<>();
        List<ProfilePicture> profilePictures = (List<ProfilePicture>) profilePictureRepository.findAll();

        for(ProfilePicture profilePicture: profilePictures){
            ProfilePictureDTO profilePictureDTO = profilePictureMapper.convertToDTO(profilePicture);
            profilePictureDTOS.add(profilePictureDTO);
        }
        return profilePictureDTOS;
    }

    @Override
    public ProfilePictureDTO findById(int id){
        return profilePictureMapper.convertToDTO(profilePictureRepository.findById(id).get());
    }

    @Override
    public ProfilePictureDTO save(ProfilePictureDTO theProfilePictureDTO){
        ProfilePicture profilePicture = profilePictureMapper.convertToEntity(theProfilePictureDTO);
        profilePictureRepository.save(profilePicture);
        ProfilePictureDTO profilePictureDTO = profilePictureMapper.convertToDTO(profilePicture);
        return profilePictureDTO;
    }

    @Override
    public void delete(ProfilePictureDTO profilePictureDTO){
        profilePictureRepository.delete(profilePictureMapper.convertToEntity(profilePictureDTO));
    }
     @Override
    public boolean deleteById(int id) {
        profilePictureRepository.deleteById(id);
        return true;
    }


}
