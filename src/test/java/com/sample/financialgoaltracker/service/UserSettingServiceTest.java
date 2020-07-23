package com.sample.financialgoaltracker.service;

import com.sample.financialgoaltracker.dto.UserSettingDTO;
import com.sample.financialgoaltracker.entity.User;
import com.sample.financialgoaltracker.entity.UserSetting;
import com.sample.financialgoaltracker.mapper.UserSettingMapper;
import com.sample.financialgoaltracker.repository.UserSettingRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserSettingServiceTest {

    @MockBean
    private UserSettingRepository userSettingRepository;

    @Autowired
    private UserSettingServiceImpl userSettingServiceImpl;

    @Autowired
    private UserSettingMapper userSettingMapper;

    private UserSetting userSetting1;
    private UserSetting userSetting2;

    @Before
    public void setUp(){
        userSetting1 = new UserSetting();
        userSetting2 = new UserSetting();

        userSetting1.setModifiedAt("12:23");
        userSetting1.setModifiedBy("ray");

        userSetting2.setModifiedAt("14:45");
        userSetting2.setModifiedBy("bruce");

        User user = new User();
        user.setName("Ray");
        user.setEmail("ray@gmail.com");
        user.setAuth0Id("12345678");
        user.setPhone("7997064795");
        user.setCountry("India");
        user.setCreatedAt("14:05");
        user.setCreatedBy("ray");
        user.setModifiedAt("16:25");
        user.setModifiedBy("ray");
        user.setDeleted(false);

        userSetting1.setUser(user);
        userSetting2.setUser(user);
    }

    @Test
    public void testFindAll(){
        List<UserSetting> userSettingsList = new ArrayList<>();

        userSettingsList.add(userSetting1);
        userSettingsList.add(userSetting2);

        //Mocking
        Mockito.when(userSettingRepository.findAll()).thenReturn(userSettingsList);

        //Test
        List<UserSettingDTO> totalUserSettings = userSettingServiceImpl.findAll();
        assertThat(2).isEqualTo(totalUserSettings.size());
    }

    @Test
    public void testFindById(){
        Mockito.when(userSettingRepository.findById(userSetting1.getId())).
                thenReturn(Optional.of(userSetting1));
        UserSettingDTO userSettingDTO = userSettingServiceImpl.findById(userSetting1.getId());
        assertThat(userSetting1).isEqualToComparingFieldByField(userSettingMapper.convertToEntity(userSettingDTO));
    }

    @Test
    public void testSave(){
        Mockito.when(userSettingRepository.save(userSetting1)).thenReturn(userSetting1);
        assertThat(userSettingMapper.convertToDTO(userSetting1)).isEqualToComparingFieldByField(userSettingServiceImpl.save(userSettingMapper.convertToDTO(userSetting1)));
    }

    @Test
    public void testDeleteById(){
        userSettingServiceImpl.deleteById(userSetting1.getId());
        Mockito.verify(userSettingRepository,Mockito.times(1))
                .deleteById(userSetting1.getId());
    }


}














