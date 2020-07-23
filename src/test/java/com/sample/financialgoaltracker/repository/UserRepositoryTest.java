package com.sample.financialgoaltracker.repository;

import com.sample.financialgoaltracker.entity.User;
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
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private User user1;

    private User user2;

    @Before
    public void testSetUp(){
        user1 = new User();
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

        user2 = new User();
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
    }

    @Test
    public void testFindAll(){
        List<User> expectedUsers = new ArrayList<>();
        expectedUsers.add(user1);
        expectedUsers.add(user2);

        userRepository.save(user1);
        userRepository.save(user2);

        List<User> actualUsers = (List<User>) userRepository.findAll();

        assertThat(actualUsers).isEqualTo(expectedUsers);
    }

    @Test
    public void testFindById(){
        User expectedUser = user1;
        userRepository.save(expectedUser);
        Optional<User> actualUser = userRepository.findById(expectedUser.getId());
        assertThat(actualUser.get()).isEqualTo(expectedUser);
    }

    @Test
    public void testSave(){
        User expectedUser = user1;
        userRepository.save(expectedUser);
        Optional<User> actualUser = userRepository.findById(expectedUser.getId());
        assertThat(actualUser.get()).isEqualTo(expectedUser);
    }

    @Test
    public void testDelete(){
        User user = user1;
        userRepository.save(user);
        Optional<User> actualUser = userRepository.findById(user.getId());
        assertThat(actualUser.get()).isEqualTo(user);
        userRepository.delete(user);
        Optional<User> returnedUser = userRepository.findById(user.getId());
        assertThat(returnedUser).isEqualTo(Optional.empty());
    }

    @Test
    public void testGetUserByAuth0Id(){
        User expectedUser = user1;
        userRepository.save(expectedUser);
        User actualUser = userRepository.getUserByAuth0Id(expectedUser.getAuth0Id());
        assertThat(actualUser).isEqualTo(expectedUser);
    }
}
