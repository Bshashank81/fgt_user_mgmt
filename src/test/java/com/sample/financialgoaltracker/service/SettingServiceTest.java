package com.sample.financialgoaltracker.service;

import com.sample.financialgoaltracker.dto.SettingDTO;
import com.sample.financialgoaltracker.entity.User;
import com.sample.financialgoaltracker.entity.Setting;
import com.sample.financialgoaltracker.mapper.SettingMapper;
import com.sample.financialgoaltracker.repository.SettingRepository;
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
public class SettingServiceTest {

    @MockBean
    private SettingRepository settingRepository;

    @Autowired
    private SettingServiceImpl settingServiceImpl;

    @Autowired
    private SettingMapper settingMapper;

    private Setting setting1;
    private Setting setting2;

    @Before
    public void setUp(){
        setting1 = new Setting();
        setting2 = new Setting();

        setting1.setSettingType1(true);
        setting1.setSettingType2(true);
        setting1.setSettingType3(true);
        setting1.setSettingType4(null);
        setting1.setSettingType5(null);
        setting1.setSettingType6(null);

        User user1 = new User();
        user1.setName("shashanks");
        user1.setEmail("shashank.balabhadra@gmail.com");
        user1.setAuth0Id("12345678");
        user1.setPhone("7997064795");
        user1.setCountry("India");
        user1.setCreatedAt("1589779112000");
        user1.setCreatedBy("shashank");
        user1.setModifiedAt("1589779112000");
        user1.setModifiedBy("shashank");
        user1.setDeleted(false);
        setting1.setUser(user1);

        setting2.setSettingType1(true);
        setting2.setSettingType2(true);
        setting2.setSettingType3(true);
        setting2.setSettingType4("Weekly");
        setting2.setSettingType5("5");
        setting2.setSettingType6("1 day");

        User user2 = new User();
        user2.setName("Bruce");
        user2.setEmail("Bruce@gmail.com");
        user2.setAuth0Id("12343456");
        user2.setPhone("7997064795");
        user2.setCountry("India");
        user2.setCreatedAt("15:25");
        user2.setCreatedBy("bruce");
        user2.setModifiedAt("18:25");
        user2.setModifiedBy("bruce");
        user2.setDeleted(false);

        setting2.setUser(user2);
    }

    @Test
    public void testFindAll(){
        List<Setting> settingsList = new ArrayList<>();

        settingsList.add(setting1);
        settingsList.add(setting2);

        //Mocking
        Mockito.when(settingRepository.findAll()).thenReturn(settingsList);

        //Test
        List<SettingDTO> totalSettings = settingServiceImpl.findAll();
        assertThat(2).isEqualTo(totalSettings.size());
    }

    @Test
    public void testFindById(){
        Mockito.when(settingRepository.findById(setting1.getId())).
                thenReturn(Optional.of(setting1));
        SettingDTO settingDTO = settingServiceImpl.findById(setting1.getId());
        assertThat(setting1).isEqualToComparingFieldByField(settingMapper.convertToEntity(settingDTO));
    }

    @Test
    public void testSave(){
        Mockito.when(settingRepository.save(setting1)).thenReturn(setting1);
        assertThat(settingMapper.convertToDTO(setting1)).isEqualToComparingFieldByField(settingServiceImpl.save(settingMapper.convertToDTO(setting1)));
    }

    @Test
    public void testDeleteById(){
        settingServiceImpl.deleteById(setting1.getId());
        Mockito.verify(settingRepository,Mockito.times(1))
                .deleteById(setting1.getId());
    }
}














