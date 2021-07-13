package com.app.developers.ws.ui.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.developers.ws.exceptions.UserServiceException;
import com.app.developers.ws.service.UserService;
import com.app.developers.ws.shared.dto.UserDto;
import com.app.developers.ws.ui.model.request.UserDetailsRequestModel;
import com.app.developers.ws.ui.model.response.ErrorMessages;
import com.app.developers.ws.ui.model.response.OperationStatusModel;
import com.app.developers.ws.ui.model.response.RequestOperationStatus;
import com.app.developers.ws.ui.model.response.UserRest;

@RestController
@RequestMapping("users") // http://localhost:8080/users
public class UserController {

	@Autowired
	UserService userService;
	
	@GetMapping(path = "/{id}",
			produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public UserRest getUser(@PathVariable String id) 
	{
		UserRest returnValue=new UserRest();
		UserDto userDto = userService.getUserByUserId(id);
		BeanUtils.copyProperties(userDto, returnValue);
		return returnValue;
	}

	@PostMapping(
			consumes =  {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE},
			produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE}
			)
	public UserRest createUser(@RequestBody UserDetailsRequestModel userDetails) throws Exception{
		
		UserRest returnValue=new UserRest();
		
		if(userDetails.getFirstName().isEmpty()) throw new NullPointerException("The Object is Null");
		
		
		UserDto userDto= new UserDto();
		BeanUtils.copyProperties(userDetails, userDto);
		
		UserDto createduser = userService.createUser(userDto);
		BeanUtils.copyProperties(createduser, returnValue);
		return returnValue;
	}

	@PutMapping(path = "/{id}",
			consumes =  {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE},
			produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE}
			)
	public UserRest updateUser(@PathVariable String id,@RequestBody UserDetailsRequestModel userDetails) throws Exception{
		
UserRest returnValue=new UserRest();
		
		if(userDetails.getFirstName().isEmpty()) throw new NullPointerException("The Object is Null");
		
		
		UserDto userDto= new UserDto();
		BeanUtils.copyProperties(userDetails, userDto);
		
		UserDto updateuser = userService.updateUser(id,userDto);
		BeanUtils.copyProperties(updateuser, returnValue);
		return returnValue;
	}

	@DeleteMapping(path = "/{id}",
			consumes =  {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE},
			produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE}
			)
	public OperationStatusModel deleteUser(@PathVariable String id) {
		OperationStatusModel returnValue = new OperationStatusModel();
		
		returnValue.setOperationName(RequestOperationName.DELETE.name());
		
		userService.deleteUser(id);
		returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
		return returnValue;
	}

}
