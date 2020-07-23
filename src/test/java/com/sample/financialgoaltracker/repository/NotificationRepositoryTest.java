
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
public class NotificationRepositoryTest {

    @Autowired
    private NotificationRepository notificationRepository;

    private Notification notification1;
    private Notification notification2;

    @Before
    public void testSetUp(){
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


        notification1.setUser(user1);

        notification2.setNotificationType1(true);
        notification2.setNotificationType2(true);
        notification2.setNotificationType3(true);
        notification2.setNotificationType4(true);
        notification2.setNotificationType5(true);
        notification2.setNotificationType6(true);
        notification2.setNotificationType7(true);

        notification2.setUser(user1);
    }

    @Test
    public void testFindAll(){
        List<Notification> expectedNotifications = new ArrayList<>();
        expectedNotifications.add(notification1);
        expectedNotifications.add(notification2);

        notificationRepository.save(notification1);
        notificationRepository.save(notification2);

        List<Notification> actualNotifications =(List<Notification>) notificationRepository.findAll();

        assertThat(actualNotifications).isEqualTo(expectedNotifications);
    }

    @Test
    public void testFindById(){
        Notification expectedNotification = notification1;
        notificationRepository.save(expectedNotification);
        Optional<Notification> actualNotification = notificationRepository.findById(expectedNotification.getId());
        assertThat(actualNotification.get()).isEqualTo(expectedNotification);
    }

    @Test
    public void testSave(){
        Notification expectedNotification = notification1;
        notificationRepository.save(expectedNotification);
        Optional<Notification> actualNotification = notificationRepository.findById(expectedNotification.getId());
        assertThat(actualNotification.get()).isEqualTo(expectedNotification);
    }

    @Test
    public void testDelete(){
        Notification notification = notification1;
        notificationRepository.save(notification);
        Optional<Notification> actualNotification = notificationRepository.findById(notification.getId());
        assertThat(actualNotification.get()).isEqualTo(notification);
        notificationRepository.delete(notification);
        Optional<Notification> returnedNotification = notificationRepository.findById(notification.getId());
        assertThat(returnedNotification).isEqualTo(Optional.empty());
    }
}
