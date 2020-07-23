
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
public class MessageRepositoryTest {

    @Autowired
    private MessageRepository messageRepository;

    private Message message1;
    private Message message2;

    @Before
    public void testSetUp(){
        message1 = new Message();
        message2 = new Message();

        message1.setEmailMessages(true);
        message1.setTextMessages(false);

        User user1 = new User();
        user1.setName("abc");
        user1.setEmail("abc.balabhadra@gmail.com");
        user1.setAuth0Id("12345678");
        user1.setPhone("917997064795");
        user1.setCountry("India");
        user1.setCreatedAt("1589779112000");
        user1.setCreatedBy("shashank");
        user1.setModifiedAt("1589779112000");
        user1.setModifiedBy("shashank");
        user1.setDeleted(false);

        message1.setUser(user1);

        message2.setEmailMessages(true);
        message2.setTextMessages(false);

        User user2 = new User();
        user2.setName("xyz");
        user2.setEmail("xyz@gmail.com");
        user2.setAuth0Id("12343456");
        user2.setPhone("7997064795");
        user2.setCountry("India");
        user2.setCreatedAt("15:25");
        user2.setCreatedBy("bruce");
        user2.setModifiedAt("18:25");
        user2.setModifiedBy("bruce");
        user2.setDeleted(false);

        message2.setUser(user2);

    }

    @Test
    public void testFindAll(){
        List<Message> expectedMessages = new ArrayList<>();
        expectedMessages.add(message1);
        expectedMessages.add(message2);

        messageRepository.save(message1);
        messageRepository.save(message2);

        List<Message> actualMessages =(List<Message>) messageRepository.findAll();

        assertThat(actualMessages).isEqualTo(expectedMessages);
    }

    @Test
    public void testFindById(){
        Message expectedMessage = message1;
        messageRepository.save(expectedMessage);
        Optional<Message> actualMessage = messageRepository.findById(expectedMessage.getId());
        assertThat(actualMessage.get()).isEqualTo(expectedMessage);
    }

    @Test
    public void testSave(){
        Message expectedMessage = message1;
        messageRepository.save(expectedMessage);
        Optional<Message> actualMessage = messageRepository.findById(expectedMessage.getId());
        assertThat(actualMessage.get()).isEqualTo(expectedMessage);
    }

    @Test
    public void testDelete(){
        Message message = message1;
        messageRepository.save(message);
        Optional<Message> actualMessage = messageRepository.findById(message.getId());
        assertThat(actualMessage.get()).isEqualTo(message);
        messageRepository.delete(message);
        Optional<Message> returnedMessage = messageRepository.findById(message.getId());
        assertThat(returnedMessage).isEqualTo(Optional.empty());
    }
}

