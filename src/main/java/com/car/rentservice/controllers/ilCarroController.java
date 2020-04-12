package com.car.rentservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.car.rentservice.dto.CarInputDTO;
import com.car.rentservice.dto.CommentInputDTO;
import com.car.rentservice.dto.ResponseModel;
import com.car.rentservice.dto.UpdateUserInputDTO;
import com.car.rentservice.service.ilCarroService;

import java.time.LocalDateTime;

@RestController
public class ilCarroController {
	@Autowired
	private ilCarroService ilCarroService;

	@DeleteMapping("/user")
	public ResponseModel deleteUser(Authentication authentication) {
		return ilCarroService.deleteUser(authentication.getName());
	}

	@PutMapping("/user")
	public ResponseModel updateUser(@RequestBody UpdateUserInputDTO userInputDTO, Authentication authentication) {
		return ilCarroService.updateUser(authentication.getName(), userInputDTO);		
	}

	@PostMapping("/car")
	public ResponseModel addCar(Authentication authentication, @RequestBody CarInputDTO carInputDTO) {
		return ilCarroService.addCar(authentication.getName(), carInputDTO);
	}

	@PutMapping("/car/update/{serialNumber}")
	public ResponseModel updateCar(Authentication authentication, @PathVariable String serialNumber,
			@RequestBody CarInputDTO carInputDTO) {
		return ilCarroService.updateCar(authentication.getName(), serialNumber, carInputDTO);
	}

	@DeleteMapping("/car/delete/{serialNumber}")
	public ResponseModel deleteCar(Authentication authentication, @PathVariable String serialNumber) {
		return ilCarroService.deleteCar(authentication.getName(), serialNumber);
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
	public ResponseModel getOwnerCarBySerialNumber(Authentication authentication, @PathVariable String serialNumber) {
		return ilCarroService.getOwnerCarBySerialNumber(authentication.getName(), serialNumber);
	}

	@GetMapping("/user/cars/periods/{serialNumber}")
	public ResponseModel getOwnerBookedPeriodsBySerialNumber(Authentication authentication,
			@PathVariable String serialNumber) {
		return ilCarroService.getOwnerBookedPeriodsBySerialNumber(authentication.getName(), serialNumber);
	}

	@GetMapping("/comments")
	public ResponseModel getLastComments() {
		return ilCarroService.getLatestComments();
	}

	@PostMapping("/comment/{serialNumber}")
	public ResponseModel addComment(Authentication authentication, @PathVariable String serialNumber,
			@RequestBody CommentInputDTO commentInputDTO) {
		return ilCarroService.addComment(authentication.getName(), serialNumber, commentInputDTO);
	}

	@GetMapping("comment/{serialNumber}")
	public ResponseModel getLastThreeCommentsBySerialNumber(@PathVariable String serialNumber) {
		return ilCarroService.getThreeLastCommentsOfCarBySerialNumber(serialNumber);
	}

	@GetMapping("car/best")
	public ResponseModel getBestCars() {
		return ilCarroService.getBestBooked();
	}

//	@GetMapping("/search/{city}/{startDateTime}/{endDateTime}/{minAmount}/{maxAmount}")
//	public ResponseModel searchCar(@PathVariable String city,
//								   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) @PathVariable LocalDateTime startDateTime,
//								   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) @PathVariable LocalDateTime endDateTime,
//								   @PathVariable int minAmount, @PathVariable int maxAmount) {
//		return ilCarroService.searchCar(city, startDateTime, endDateTime, minAmount, maxAmount);
//	}

	@GetMapping("/search/filters/{make}/{modal}/{year}/{engine}/{fuel}/{gear}/{wheelsDrive}")
	public ResponseModel searchByFilters(@PathVariable String make,
										 @PathVariable String modal,
										 @PathVariable String year,
										 @PathVariable String engine,
										 @PathVariable String fuel,
										 @PathVariable String gear,
										 @PathVariable String wheelsDrive){
		return ilCarroService.searchCarByFilters(make,modal,year,engine,fuel,gear,wheelsDrive);
	}
}