package com.sample.financialgoaltracker.controller;

import com.sample.financialgoaltracker.dto.SettingDTO;
import com.sample.financialgoaltracker.dto.UserDTO;
import com.sample.financialgoaltracker.mapper.UserMapper;
import com.sample.financialgoaltracker.service.SettingService;
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
@RequestMapping("/api/setting")
public class SettingController {

    @Autowired
    private SettingService settingService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @ApiOperation(value = "View a list of available settings",response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })

    @RequestMapping(value = "/list", method= RequestMethod.GET, produces = "application/json")
    public Iterable<SettingDTO> list(Model model){
        Iterable<SettingDTO> settingList = settingService.findAll();
        return settingList;
    }

    @ApiOperation(value = "Search a setting with an ID",response = SettingDTO.class)
    @RequestMapping(value = "/show/{id}", method= RequestMethod.GET, produces = "application/json")
    public SettingDTO showSetting(@PathVariable Integer id, Model model){
        SettingDTO settingdto = settingService.findById(id);
        return settingdto;
    }

    @ApiOperation(value = "Add a setting")
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity saveSetting(@RequestBody SettingDTO settingDTO){
        settingService.save(settingDTO);
        return new ResponseEntity("Setting saved successfully", HttpStatus.OK);
    }

    @ApiOperation(value = "Update a setting")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity updateSetting(@PathVariable Integer id, @RequestBody SettingDTO settingDTO) {
        SettingDTO storedSetting = settingService.findById(id);
        storedSetting.setSettingType1(true);
        storedSetting.setSettingType2(true);
        storedSetting.setSettingType3(true);
        storedSetting.setSettingType4("Monthly");
        storedSetting.setSettingType5("1");
        storedSetting.setSettingType6("1 Day");

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

        storedSetting.setUser(userMapper.convertToEntity(storedUser));

        return new ResponseEntity("Setting updated successfully", HttpStatus.OK);
    }

    @ApiOperation(value = "Delete a setting")
    @RequestMapping(value="/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity delete(@PathVariable Integer id){
        settingService.deleteById(id);
        return new ResponseEntity("Setting deleted successfully", HttpStatus.OK);
    }
}





















