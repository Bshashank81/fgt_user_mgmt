package com.sample.financialgoaltracker.controller;
import com.sample.financialgoaltracker.dto.*;
import com.sample.financialgoaltracker.entity.ProfilePicture;
import com.sample.financialgoaltracker.exception.UserNotFoundException;
import com.sample.financialgoaltracker.service.ProfilePictureService;
import com.sample.financialgoaltracker.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.models.Model;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;

@RestController
@Slf4j
@CrossOrigin
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProfilePictureService profilePictureService;

    @Autowired
    private SuccessResponse successResponse;


    @RequestMapping(value = "/users", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity fetchUserByAuthId(HttpServletRequest request) {

        String authId = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info("===>>>>fetching user");
        UserDTO userDTO = userService.getUserByAuth0Id(authId);
        if (userDTO==null||userDTO.getEmail() == null||userDTO.isDeleted())
            throw new UserNotFoundException("-----User not found----");
        //ProfilePictureDTO profilePictureDTO = profilePictureService.findById(userDTO.getId());
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
        return ResponseEntity.ok().headers(headers).body(userDTO);

    }

    @RequestMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE,  method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity updateUser(HttpServletRequest request,@RequestBody UserDTO userDTO) {

        UserDTO userDTO1=userService.save(userDTO);
        log.info("=====>>>>updated user{}",userDTO1.getId());
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
        return ResponseEntity.ok().headers(headers).body(userDTO1);
    }


    @RequestMapping(value = "/users", method = RequestMethod.DELETE, produces = "application/json")
    @ResponseBody
    public ResponseEntity deleteUser(HttpServletRequest request) {
        String authId = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDTO userDTO = userService.getUserByAuth0Id(authId);
        userDTO.setDeleted(true);
        userDTO = userService.save(userDTO);
        log.info("=====>>>>deleted user {}",userDTO.getId());
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        SuccessResponse successResponse = new SuccessResponse();
        return successResponse.sendSuccessResponse("User is deleted successfully",timestamp.getTime());
    }



 

    @ApiOperation(value = "View a list of available users", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
        }
    )

    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json")
    public Iterable<UserDTO> list(Model model) {
        Iterable<UserDTO> userList = userService.findAll();
        return userList;
    }

    @ApiOperation(value = "Search a user with an ID", response = UserDTO.class)
    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET, produces = "application/json")
    public UserDTO showUser(@PathVariable Integer id, Model model) {
        UserDTO userdto = userService.findById(id);
        return userdto;
    }

    @ApiOperation(value = "Add a user")
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity saveUser(@RequestBody UserDTO userDTO) {
        userService.save(userDTO);
        return new ResponseEntity("User saved successfully", HttpStatus.OK);
    }

    @ApiOperation(value = "Update a user")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity updateAUser(@PathVariable Integer id, @RequestBody UserDTO userDTO) {
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
        return new ResponseEntity("User updated successfully", HttpStatus.OK);
    }


    @ApiOperation(value = "Delete a user")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity deleteAUser(@PathVariable Integer id) {
        userService.deleteById(id);
        return new ResponseEntity("User deleted successfully", HttpStatus.OK);
    }
}



