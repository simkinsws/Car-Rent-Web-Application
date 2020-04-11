package com.car.rentservice.controllers;

import java.util.ArrayList;
import java.util.Arrays;

import com.car.rentservice.dto.*;
import com.car.rentservice.modal.Comments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

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
			e.printStackTrace();
			responseModel.setMessage("Unable to delete: " + e.getMessage());
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
		if (carInputDTO != null && carInputDTO.getSerialNumber().length() >= 7
				&& carInputDTO.getSerialNumber().length() <= 8) {
			CarOwnerOutputDTO car = ilCarroService.addCar(authentication.getName(), carInputDTO);
			if (car == null) {
				responseModel.setStatus(HttpStatus.UNAUTHORIZED.toString());
			} else {
				responseModel.setStatus(HttpStatus.OK.toString());
				responseModel.setDataList(new ArrayList<>(Arrays.asList(car)));
			}
		} else {
			responseModel.setStatus(HttpStatus.CONFLICT.toString());
			responseModel.setMessage("Enter valid serial number ");
			responseModel.setDataList(null);
		}

		return responseModel;
	}

	@PutMapping("/car/update/{serialNumber}")
	public ResponseModel updateCar(Authentication authentication, @PathVariable String serialNumber,
			@RequestBody CarInputDTO carInputDTO) {
		ResponseModel responseModel = new ResponseModel();
		try {
			responseModel = ilCarroService.updateCar(authentication.getName(), serialNumber, carInputDTO);

		} catch (Exception e) {
			responseModel.setStatus(HttpStatus.UNAUTHORIZED.toString());
			responseModel.setMessage("Unexpected exception occurs: " + e.getMessage());
			responseModel.setDataList(null);
		}
		return responseModel;
	}

	@DeleteMapping("/car/delete/{serialNumber}")
	public ResponseModel deleteCar(Authentication authentication, @PathVariable String serialNumber) {
		ResponseModel responseModel = new ResponseModel();
		String data = null;
		try {
			responseModel = ilCarroService.deleteCar(authentication.getName(), serialNumber);
		} catch (Exception e) {
			responseModel.setStatus(HttpStatus.UNAUTHORIZED.toString());
			responseModel.setMessage(data);
			responseModel.setDataList(null);
		}
		return responseModel;
	}

	@GetMapping("/car/get/{serialNumber}")
	public ResponseModel getCarBySerialNumber(@PathVariable String serialNumber) {
		return ilCarroService.getCarBySerialNumber(serialNumber);
	}

	@GetMapping("/user/cars")
	public ResponseModel getOwnerCars(Authentication authentication) {
		return ilCarroService.getOwnerCars(authentication.getName());
	}

	@GetMapping("user/cars/car/{serialNumber}")
	public ResponseModel getOwnerCarBySerialNumber(Authentication authentication,@PathVariable String serialNumber) {
		return ilCarroService.getOwnerCarBySerialNumber(authentication.getName(),serialNumber);
	}

	@GetMapping("/user/cars/periods/{serialNumber}")
	public ResponseModel getOwnerBookedPeriodsBySerialNumber(Authentication authentication,
															 @PathVariable String serialNumber) {
		return ilCarroService.getOwnerBookedPeriodsBySerialNumber(authentication.getName(),serialNumber);
	}

	@GetMapping("/comments")
	public ResponseModel getLastComments() {
		return ilCarroService.getLatestComments();
	}

	@PostMapping("/comment/{serialNumber}")
	public ResponseModel addComment(Authentication authentication,
									@PathVariable String serialNumber,
									@RequestBody CommentInputDTO commentInputDTO) {
		return ilCarroService.addComment(authentication.getName(),serialNumber,commentInputDTO);
	}

}