package com.sample.financialgoaltracker.service;

import com.sample.financialgoaltracker.dto.MessageDTO;

import com.sample.financialgoaltracker.dto.UserDTO;
import com.sample.financialgoaltracker.entity.Message;
import com.sample.financialgoaltracker.entity.User;

import com.sample.financialgoaltracker.entity.Message;

import com.sample.financialgoaltracker.mapper.MessageMapper;
import com.sample.financialgoaltracker.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private MessageMapper messageMapper;

    @Override
    public List<MessageDTO> findAll(){
        List<MessageDTO> messageDTOS = new ArrayList<>();
        List<Message> messages = (List<Message>) messageRepository.findAll();

        for(Message message: messages){
            MessageDTO messageDTO = messageMapper.convertToDTO(message);
            messageDTOS.add(messageDTO);
        }
        return messageDTOS;
    }

    @Override
    public MessageDTO findById(int id){
        return messageMapper.convertToDTO(messageRepository.findById(id).get());
    }

    @Override
    public MessageDTO findByUserId(int id){
        Message message= messageRepository.findByUserId(id);
        if(message==null){
            return null;
        }
        else {
            return messageMapper.convertToDTO(message);
        }
    }



    @Override
    public MessageDTO save(MessageDTO theMessageDTO){
        Message message = messageMapper.convertToEntity(theMessageDTO);
        messageRepository.save(message);
        MessageDTO messageDTO = messageMapper.convertToDTO(message);
        return messageDTO;
    }

    @Override
    public void delete(MessageDTO messageDTO){
        messageRepository.delete(messageMapper.convertToEntity(messageDTO));
    }
     @Override
    public boolean deleteById(int id) {
        messageRepository.deleteById(id);
        return true;
    }


}
