package com.car.rentservice.controllers;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.car.rentservice.dto.CarInputDTO;
import com.car.rentservice.dto.CarOwnerOutputDTO;
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
			responseModel.setMessage("Got the null user: " + user);
		} else {
			responseModel.setStatus(HttpStatus.OK.toString());
			responseModel.setMessage("User updated " + user.getFirstName());
			responseModel.setDataList(new ArrayList<Object>(Arrays.asList(user)));
		}
		return responseModel;
	}

	@PostMapping("/car")
	public ResponseModel addCar(Authentication authentication, @RequestBody CarInputDTO carInputDTO) {
		ResponseModel responseModel = new ResponseModel();
		CarOwnerOutputDTO car = ilCarroService.addCar(authentication.getName(), carInputDTO);
		if (car == null) {
			responseModel.setStatus(HttpStatus.UNAUTHORIZED.toString());
		} else {
			responseModel.setStatus(HttpStatus.OK.toString());
			responseModel.setDataList(new ArrayList<>(Arrays.asList(car)));
		}
		return responseModel;
	}

	@DeleteMapping("/car/delete/{serialNumber}")
	public ResponseModel deleteCar(Authentication authentication, @PathVariable String serialNumber) {
		ResponseModel responseModel = new ResponseModel();
		String data = null;
		try {
			data = ilCarroService.deleteCar(authentication.getName(), serialNumber);
			responseModel.setStatus(HttpStatus.OK.toString());
			responseModel.setDataList(null);
		} catch (Exception e) {
			responseModel.setStatus(HttpStatus.UNAUTHORIZED.toString());
			responseModel.setDataList(new ArrayList<Object>(Arrays.asList(data)));
		}
		return responseModel;
	}

}