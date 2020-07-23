package com.sample.financialgoaltracker.controller;

import com.sample.financialgoaltracker.dto.NotificationDTO;
import com.sample.financialgoaltracker.dto.UserDTO;
import com.sample.financialgoaltracker.mapper.UserMapper;
import com.sample.financialgoaltracker.service.NotificationService;
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
@RequestMapping("/api/notification")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @ApiOperation(value = "View a list of available notifications",response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })

    @RequestMapping(value = "/list", method= RequestMethod.GET, produces = "application/json")
    public Iterable<NotificationDTO> list(Model model){
        Iterable<NotificationDTO> notificationList = notificationService.findAll();
        return notificationList;
    }

    @ApiOperation(value = "Search a notification with an ID",response = NotificationDTO.class)
    @RequestMapping(value = "/show/{id}", method= RequestMethod.GET, produces = "application/json")
    public NotificationDTO showNotification(@PathVariable Integer id, Model model){
        NotificationDTO notificationdto = notificationService.findById(id);
        return notificationdto;
    }

    @ApiOperation(value = "Add a notification")
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity saveNotification(@RequestBody NotificationDTO notificationDTO){
        notificationService.save(notificationDTO);
        return new ResponseEntity("Notification saved successfully", HttpStatus.OK);
    }

    @ApiOperation(value = "Update a notification")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity updateNotification(@PathVariable Integer id, @RequestBody NotificationDTO notificationDTO) {
        NotificationDTO storedNotification = notificationService.findById(id);
        storedNotification.setNotificationType1(true);
        storedNotification.setNotificationType2(true);
        storedNotification.setNotificationType3(true);
        storedNotification.setNotificationType4(true);
        storedNotification.setNotificationType5(true);
        storedNotification.setNotificationType6(true);
        storedNotification.setNotificationType7(true);

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

        storedNotification.setUser(userMapper.convertToEntity(storedUser));

        return new ResponseEntity("Notification updated successfully", HttpStatus.OK);
    }

    @ApiOperation(value = "Delete a notification")
    @RequestMapping(value="/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity delete(@PathVariable Integer id){
        notificationService.deleteById(id);
        return new ResponseEntity("Notification deleted successfully", HttpStatus.OK);
    }
}





















