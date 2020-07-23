package com.sample.financialgoaltracker.service;

import com.sample.financialgoaltracker.dto.NotificationDTO;
import com.sample.financialgoaltracker.entity.User;
import com.sample.financialgoaltracker.entity.Notification;
import com.sample.financialgoaltracker.mapper.NotificationMapper;
import com.sample.financialgoaltracker.repository.NotificationRepository;
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
public class NotificationServiceTest {

    @MockBean
    private NotificationRepository notificationRepository;

    @Autowired
    private NotificationServiceImpl notificationServiceImpl;

    @Autowired
    private NotificationMapper notificationMapper;

    private Notification notification1;
    private Notification notification2;

    @Before
    public void setUp(){
        notification1 = new Notification();
        notification2 = new Notification();

        notification1.setNotificationType1(true);
        notification1.setNotificationType2(true);
        notification1.setNotificationType3(true);
        notification1.setNotificationType4(true);
        notification1.setNotificationType5(true);
        notification1.setNotificationType6(true);
        notification1.setNotificationType7(true);

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
        notification1.setUser(user1);

        notification2.setNotificationType1(true);
        notification2.setNotificationType2(true);
        notification2.setNotificationType3(true);
        notification2.setNotificationType4(true);
        notification2.setNotificationType5(true);
        notification2.setNotificationType6(true);
        notification2.setNotificationType7(true);

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

        notification2.setUser(user2);
    }

    @Test
    public void testFindAll(){
        List<Notification> notificationsList = new ArrayList<>();

        notificationsList.add(notification1);
        notificationsList.add(notification2);

        //Mocking
        Mockito.when(notificationRepository.findAll()).thenReturn(notificationsList);

        //Test
        List<NotificationDTO> totalNotifications = notificationServiceImpl.findAll();
        assertThat(2).isEqualTo(totalNotifications.size());
    }

    @Test
    public void testFindById(){
        Mockito.when(notificationRepository.findById(notification1.getId())).
                thenReturn(Optional.of(notification1));
        NotificationDTO notificationDTO = notificationServiceImpl.findById(notification1.getId());
        assertThat(notification1).isEqualToComparingFieldByField(notificationMapper.convertToEntity(notificationDTO));
    }

    @Test
    public void testSave(){
        Mockito.when(notificationRepository.save(notification1)).thenReturn(notification1);
        assertThat(notificationMapper.convertToDTO(notification1)).isEqualToComparingFieldByField(notificationServiceImpl.save(notificationMapper.convertToDTO(notification1)));
    }

    @Test
    public void testDeleteById(){
        notificationServiceImpl.deleteById(notification1.getId());
        Mockito.verify(notificationRepository,Mockito.times(1))
                .deleteById(notification1.getId());
    }
}














