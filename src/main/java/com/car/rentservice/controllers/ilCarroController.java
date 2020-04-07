package com.car.rentservice.controllers;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.car.rentservice.dto.ResponseModel;
import com.car.rentservice.dto.UpdateUserInputDTO;
import com.car.rentservice.dto.UserSuccessResponseDTO;
import com.car.rentservice.service.ilCarroService;

@RestController
public class ilCarroController {
	@Autowired
	private ilCarroService ilCarroService;

	@DeleteMapping("/user")
	public ResponseModel deleteUser(Authentication authentication) {
		ResponseModel responseModel = new ResponseModel();
		String data = null;
		try {
			data = ilCarroService.deleteUser(authentication.getName());
			responseModel.setStatus(HttpStatus.OK.toString());
			responseModel.setDataList(null);
		} catch (Exception e) {
			responseModel.setStatus(HttpStatus.UNAUTHORIZED.toString());
			responseModel.setDataList(new ArrayList<Object>(Arrays.asList(data)));
		}
		return responseModel;
	}

	@PutMapping("/user")
	public ResponseModel updateUser(@RequestBody UpdateUserInputDTO userInputDTO, Authentication authentication) {
		ResponseModel responseModel = new ResponseModel();
		UserSuccessResponseDTO user = ilCarroService.updateUser(authentication.getName(), userInputDTO);
		if (user == null) {
			responseModel.setStatus(HttpStatus.UNAUTHORIZED.toString());
		} else {
			responseModel.setStatus(HttpStatus.OK.toString());
			responseModel.setDataList(new ArrayList<Object>(Arrays.asList(user)));
		}
		return responseModel;
	}

}