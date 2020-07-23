package com.sample.financialgoaltracker.service;

import com.sample.financialgoaltracker.dto.UserDTO;
import com.sample.financialgoaltracker.entity.User;
import com.sample.financialgoaltracker.mapper.UserMapper;
import com.sample.financialgoaltracker.repository.UserRepository;
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
public class UserServiceTest {

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private UserMapper userMapper;

    private User user1;
    private User user2;

    @Before
    public void setUp(){
        user1 = new User();
        user1.setName("Shashank");
        user1.setEmail("shashank.balabhadra@zemosolabs.com");
        user1.setAuth0Id("12345678");
        user1.setPhone("917997064795");
        user1.setCountry("India");
        user1.setCreatedAt("1589626102282");
        user1.setCreatedBy("Shashank");
        user1.setModifiedAt("1589626102282");
        user1.setModifiedBy("Shashank");
        user1.setDeleted(false);

        user2 = new User();
        user2.setName("Bruce");
        user2.setEmail("bruce@gmail.com");
        user2.setAuth0Id("22343456");
        user2.setPhone("8997064795");
        user2.setCountry("India");
        user2.setCreatedAt("1589779114000");
        user2.setCreatedBy("bruce");
        user2.setModifiedAt("1589779118000");
        user2.setModifiedBy("bruce");
        user2.setDeleted(false);
    }

    @Test
    public void testFindAll(){
        List<User> usersList = new ArrayList<>();

        usersList.add(user1);
        usersList.add(user2);

        //Mocking
        Mockito.when(userRepository.findAll()).thenReturn(usersList);

        //Test
        List<UserDTO> totalUsers = userServiceImpl.findAll();
        assertThat(2).isEqualTo(totalUsers.size());
    }

    @Test
    public void testFindById(){
        Mockito.when(userRepository.findById(user1.getId())).
                thenReturn(Optional.of(user1));
        UserDTO userDTO = userServiceImpl.findById(user1.getId());
        assertThat(user1).isEqualToComparingFieldByField(userMapper.convertToEntity(userDTO));
    }

    @Test
    public void testSave(){
        Mockito.when(userRepository.save(user1)).thenReturn(user1);
        assertThat(userMapper.convertToDTO(user1)).isEqualToComparingFieldByField(userServiceImpl.save(userMapper.convertToDTO(user1)));
    }

    @Test
    public void testDeleteById(){
        userServiceImpl.deleteById(user1.getId());
        Mockito.verify(userRepository,Mockito.times(1))
                .deleteById(user1.getId());
    }

    @Test
    public void testGetUserByAuth0Id(){
        Mockito.when(userRepository.getUserByAuth0Id(user1.getAuth0Id()))
                .thenReturn(user1);
        UserDTO userDTO = userServiceImpl.getUserByAuth0Id(user1.getAuth0Id());
        assertThat(user1).isEqualToComparingFieldByField(userMapper.convertToEntity(userDTO));

    }
}














