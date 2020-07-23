
package com.sample.financialgoaltracker.repository;

import com.sample.financialgoaltracker.entity.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@RunWith(SpringRunner.class)
@DataJpaTest
public class SettingRepositoryTest {

    @Autowired
    private SettingRepository settingRepository;

    private Setting setting1;
    private Setting setting2;

    @Before
    public void testSetUp(){
        setting1 = new Setting();
        setting2 = new Setting();

        setting1.setSettingType1(true);
        setting1.setSettingType2(true);
        setting1.setSettingType3(true);
        setting1.setSettingType4("Monthly");
        setting1.setSettingType5("1");
        setting1.setSettingType6("1 day");

        User user1 = new User();
        user1.setName("shashank");
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

        setting2.setUser(user1);
    }

    @Test
    public void testFindAll(){
        List<Setting> expectedSettings = new ArrayList<>();
        expectedSettings.add(setting1);
        expectedSettings.add(setting2);

        settingRepository.save(setting1);
        settingRepository.save(setting2);

        List<Setting> actualSettings =(List<Setting>) settingRepository.findAll();

        assertThat(actualSettings).isEqualTo(expectedSettings);
    }

    @Test
    public void testFindById(){
        Setting expectedSetting = setting1;
        settingRepository.save(expectedSetting);
        Optional<Setting> actualSetting = settingRepository.findById(expectedSetting.getId());
        assertThat(actualSetting.get()).isEqualTo(expectedSetting);
    }

    @Test
    public void testSave(){
        Setting expectedSetting = setting1;
        settingRepository.save(expectedSetting);
        Optional<Setting> actualSetting = settingRepository.findById(expectedSetting.getId());
        assertThat(actualSetting.get()).isEqualTo(expectedSetting);
    }

    @Test
    public void testDelete(){
        Setting setting = setting1;
        settingRepository.save(setting);
        Optional<Setting> actualSetting = settingRepository.findById(setting.getId());
        assertThat(actualSetting.get()).isEqualTo(setting);
        settingRepository.delete(setting);
        Optional<Setting> returnedSetting = settingRepository.findById(setting.getId());
        assertThat(returnedSetting).isEqualTo(Optional.empty());
    }
}

