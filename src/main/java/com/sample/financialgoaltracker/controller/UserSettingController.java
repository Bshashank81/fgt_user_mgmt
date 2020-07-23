package com.sample.financialgoaltracker.controller;
import com.sample.financialgoaltracker.dto.*;
import com.sample.financialgoaltracker.entity.Notification;
import com.sample.financialgoaltracker.exception.UserNotFoundException;
import com.sample.financialgoaltracker.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.sample.financialgoaltracker.mapper.UserMapper;
import com.sample.financialgoaltracker.service.UserService;
import com.sample.financialgoaltracker.service.UserSettingService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.models.Model;
import lombok.extern.slf4j.Slf4j;



@RestController
@Slf4j
@CrossOrigin
@RequestMapping("/api/usersetting")
public class UserSettingController {

    @Autowired
    private UserSettingService userSettingService;

    @Autowired
    private SuccessResponse successResponse;
    @Autowired
    private MessageService messageService;
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private SettingService settingService;

    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;

    @ApiOperation(value = "View a list of available userSettings",response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })


    @RequestMapping(value = "/settings", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity fetchSetting(HttpServletRequest request) {

        String authId = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        UserDTO userDTO = userService.getUserByAuth0Id(authId);

        if (userDTO == null)
            throw new UserNotFoundException("-----User not found----");

        log.info("===>>>>fetching settings of user id {}",userDTO.getId());
        MessageDTO messageDTO=messageService.findByUserId(userDTO.getId());
        NotificationDTO notificationDTO=notificationService.findByUserId(userDTO.getId());
        SettingDTO settingDTO = settingService.findByUser(userDTO.getId());
        log.info("====>>>message {}",messageDTO.getId());
//        log.info("====>>>notification {}",notificationDTO.getId());
//        log.info("====>>>setting {}",settingDTO.getId());
        AllSettingsDto allSettingsDto = new AllSettingsDto(messageDTO,notificationDTO,settingDTO);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
        return ResponseEntity.ok().headers(headers).body(allSettingsDto);
    }


    @RequestMapping(value = "/settings", method = RequestMethod.PUT, produces = "application/json")
    @ResponseBody
    public ResponseEntity updateSetting(HttpServletRequest request,@RequestBody AllSettingsDto allSettingsDto) {

        MessageDTO messageDTO= (MessageDTO) allSettingsDto.getMessage();
        NotificationDTO notificationDTO= (NotificationDTO) allSettingsDto.getNotification();
        SettingDTO settingDTO = (SettingDTO) allSettingsDto.getSetting();

        log.info("===>>>>updating settings");

        settingService.save(settingDTO);
        notificationService.save(notificationDTO);
        messageService.save(messageDTO);

        AllSettingsDto allSettingsDto1 = new AllSettingsDto(messageDTO,notificationDTO,settingDTO);


        if (messageDTO == null)
            throw new UserNotFoundException("-----User not found----");
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
        return ResponseEntity.ok().headers(headers).body(allSettingsDto1);
    }


    
    @RequestMapping(value = "/list", method= RequestMethod.GET, produces = "application/json")
    public Iterable<UserSettingDTO> list(Model model){
        Iterable<UserSettingDTO> userSettingList = userSettingService.findAll();
        return userSettingList;
    }

    @ApiOperation(value = "Search a userSetting with an ID",response = UserSettingDTO.class)
    @RequestMapping(value = "/show/{id}", method= RequestMethod.GET, produces = "application/json")
    public UserSettingDTO showUserSetting(@PathVariable Integer id, Model model){
        UserSettingDTO userSettingdto = userSettingService.findById(id);
        return userSettingdto;
    }

    @ApiOperation(value = "Add a userSetting")
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity saveUserSetting(@RequestBody UserSettingDTO userSettingDTO){
        userSettingService.save(userSettingDTO);
        return new ResponseEntity("UserSetting saved successfully", HttpStatus.OK);
    }

    @ApiOperation(value = "Update a userSetting")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity updateUserSetting(@PathVariable Integer id, @RequestBody UserSettingDTO userSettingDTO) {
        UserSettingDTO storedUserSetting = userSettingService.findById(id);
        storedUserSetting.setModifiedAt("1590067809000");
        storedUserSetting.setModifiedBy("shashank");

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

        storedUserSetting.setUser(userMapper.convertToEntity(storedUser));

        return new ResponseEntity("UserSetting updated successfully", HttpStatus.OK);
    }

    @ApiOperation(value = "Delete a userSetting")
    @RequestMapping(value="/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity delete(@PathVariable Integer id){
        userSettingService.deleteById(id);
        return new ResponseEntity("UserSetting deleted successfully", HttpStatus.OK);
    }

}





















