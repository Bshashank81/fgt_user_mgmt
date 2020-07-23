package com.sample.financialgoaltracker.controller;

import com.sample.financialgoaltracker.dto.MessageDTO;
import com.sample.financialgoaltracker.dto.UserDTO;
import com.sample.financialgoaltracker.mapper.UserMapper;
import com.sample.financialgoaltracker.service.MessageService;
import com.sample.financialgoaltracker.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.models.Model;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@CrossOrigin
@RequestMapping("/api/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @ApiOperation(value = "View a list of available messages",response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })

    @RequestMapping(value = "/list", method= RequestMethod.GET, produces = "application/json")
    public Iterable<MessageDTO> list(Model model){
        Iterable<MessageDTO> messageList = messageService.findAll();
        return messageList;
    }

    @ApiOperation(value = "Search a message with an ID",response = MessageDTO.class)
    @RequestMapping(value = "/show/{id}", method= RequestMethod.GET, produces = "application/json")
    public MessageDTO showMessage(@PathVariable Integer id, Model model){
        MessageDTO messagedto = messageService.findById(id);
        return messagedto;
    }

    @ApiOperation(value = "Add a message")
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity saveMessage(@RequestBody MessageDTO messageDTO){
        messageService.save(messageDTO);
        return new ResponseEntity("Message saved successfully", HttpStatus.OK);
    }

    @ApiOperation(value = "Update a message")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity updateMessage(@PathVariable Integer id, @RequestBody MessageDTO messageDTO) {
        MessageDTO storedMessage = messageService.findById(id);


        UserDTO userDTO = new UserDTO();
        UserDTO storedUser = userService.findById(id);
        storedUser.setAuth0Id(userDTO.getAuth0Id());
        storedUser.setCountry(userDTO.getCountry());
        storedUser.setCreatedAt(userDTO.getCreatedAt());
        storedUser.setCreatedBy(userDTO.getCreatedBy());
        storedUser.setDeleted(userDTO.isDeleted());
        storedUser.setEmail(userDTO.getEmail());
        storedUser.setModifiedAt(userDTO.getModifiedAt());
        storedUser.setModifiedBy(userDTO.getModifiedBy());
        storedUser.setPhone(userDTO.getPhone());
        storedUser.setName(userDTO.getName());

        storedMessage.setUser(userMapper.convertToEntity(storedUser));

        return new ResponseEntity("Message updated successfully", HttpStatus.OK);
    }

    @ApiOperation(value = "Delete a message")
    @RequestMapping(value="/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity delete(@PathVariable Integer id){
        messageService.deleteById(id);
        return new ResponseEntity("Message deleted successfully", HttpStatus.OK);
    }
}





















