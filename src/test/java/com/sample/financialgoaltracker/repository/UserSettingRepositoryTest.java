package com.sample.financialgoaltracker.repository;

import com.sample.financialgoaltracker.entity.User;
import com.sample.financialgoaltracker.entity.UserSetting;
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
public class UserSettingRepositoryTest {

    @Autowired
    private UserSettingRepository userSettingRepository;

    private UserSetting userSetting1;
    private UserSetting userSetting2;

    @Before
    public void testSetUp(){
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
        List<UserSetting> expectedUserSettings = new ArrayList<>();
        expectedUserSettings.add(userSetting1);
        expectedUserSettings.add(userSetting2);

        userSettingRepository.save(userSetting1);
        userSettingRepository.save(userSetting2);

        List<UserSetting> actualUserSettings =(List<UserSetting>) userSettingRepository.findAll();

        assertThat(actualUserSettings).isEqualTo(expectedUserSettings);
    }

    @Test
    public void testFindById(){
        UserSetting expectedUserSetting = userSetting1;
        userSettingRepository.save(expectedUserSetting);
        Optional<UserSetting> actualUserSetting = userSettingRepository.findById(expectedUserSetting.getId());
        assertThat(actualUserSetting.get()).isEqualTo(expectedUserSetting);
    }

    @Test
    public void testSave(){
        UserSetting expectedUserSetting = userSetting1;
        userSettingRepository.save(expectedUserSetting);
        Optional<UserSetting> actualUserSetting = userSettingRepository.findById(expectedUserSetting.getId());
        assertThat(actualUserSetting.get()).isEqualTo(expectedUserSetting);
    }

    @Test
    public void testDelete(){
        UserSetting userSetting = userSetting1;
        userSettingRepository.save(userSetting);
        Optional<UserSetting> actualUserSetting = userSettingRepository.findById(userSetting.getId());
        assertThat(actualUserSetting.get()).isEqualTo(userSetting);
        userSettingRepository.delete(userSetting);
        Optional<UserSetting> returnedUserSetting = userSettingRepository.findById(userSetting.getId());
        assertThat(returnedUserSetting).isEqualTo(Optional.empty());
    }
}
