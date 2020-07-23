package com.sample.financialgoaltracker.mapper;

import com.sample.financialgoaltracker.dto.MessageDTO;
import com.sample.financialgoaltracker.entity.Message;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageMapper {

    @Autowired
    private ModelMapper modelMapper;

    public Message convertToEntity(MessageDTO messageDTO){
        return modelMapper.map(messageDTO, Message.class);
    }

    public MessageDTO convertToDTO(Message message){
        return modelMapper.map(message, MessageDTO.class);
    }
}
