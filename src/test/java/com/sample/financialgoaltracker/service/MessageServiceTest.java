package com.sample.financialgoaltracker.service;

import com.sample.financialgoaltracker.dto.MessageDTO;
import com.sample.financialgoaltracker.entity.User;
import com.sample.financialgoaltracker.entity.Message;
import com.sample.financialgoaltracker.mapper.MessageMapper;
import com.sample.financialgoaltracker.repository.MessageRepository;
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
public class MessageServiceTest {

    @MockBean
    private MessageRepository messageRepository;

    @Autowired
    private MessageServiceImpl messageServiceImpl;

    @Autowired
    private MessageMapper messageMapper;

    private Message message1;
    private Message message2;

    @Before
    public void setUp(){
        message1 = new Message();
        message2 = new Message();

        message1.setEmailMessages(true);
        message1.setTextMessages(false);

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
        message1.setUser(user1);

        message1.setEmailMessages(true);
        message1.setTextMessages(false);

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

        message2.setUser(user2);
    }

    @Test
    public void testFindAll(){
        List<Message> messagesList = new ArrayList<>();

        messagesList.add(message1);
        messagesList.add(message2);

        //Mocking
        Mockito.when(messageRepository.findAll()).thenReturn(messagesList);

        //Test
        List<MessageDTO> totalMessages = messageServiceImpl.findAll();
        assertThat(2).isEqualTo(totalMessages.size());
    }

    @Test
    public void testFindById(){
        Mockito.when(messageRepository.findById(message1.getId())).
                thenReturn(Optional.of(message1));
        MessageDTO messageDTO = messageServiceImpl.findById(message1.getId());
        assertThat(message1).isEqualToComparingFieldByField(messageMapper.convertToEntity(messageDTO));
    }

    @Test
    public void testSave(){
        Mockito.when(messageRepository.save(message1)).thenReturn(message1);
        assertThat(messageMapper.convertToDTO(message1)).isEqualToComparingFieldByField(messageServiceImpl.save(messageMapper.convertToDTO(message1)));
    }

    @Test
    public void testDeleteById(){
        messageServiceImpl.deleteById(message1.getId());
        Mockito.verify(messageRepository,Mockito.times(1))
                .deleteById(message1.getId());
    }
}














