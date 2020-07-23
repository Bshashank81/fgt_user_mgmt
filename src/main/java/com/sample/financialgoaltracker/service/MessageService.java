package com.sample.financialgoaltracker.service;

import com.sample.financialgoaltracker.dto.MessageDTO;

import java.util.List;

public interface MessageService {
    List<MessageDTO> findAll();

    MessageDTO findById(int id);

    MessageDTO findByUserId(int id);

    MessageDTO save(MessageDTO messageDTO);
    void delete(MessageDTO messageDTO);
    boolean deleteById(int id);

}
