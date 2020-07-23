package com.sample.financialgoaltracker.repository;

import com.sample.financialgoaltracker.entity.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<Message, Integer> {
    Message findByUserId(int id);
}
