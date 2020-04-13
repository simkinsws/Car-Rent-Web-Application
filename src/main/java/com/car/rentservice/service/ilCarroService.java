package com.car.rentservice.service;

import com.car.rentservice.dto.CarInputDTO;
import com.car.rentservice.dto.CommentInputDTO;
import com.car.rentservice.dto.ResponseModel;
import com.car.rentservice.dto.UpdateUserInputDTO;

public interface ilCarroService {
	ResponseModel updateUser(String email, UpdateUserInputDTO updateUserInputDTO);

	ResponseModel deleteUser(String email);

	ResponseModel addCar(String email, CarInputDTO carInputDTO);

	ResponseModel deleteCar(String email, String serialNumber);

	ResponseModel updateCar(String email, String serialNumber, CarInputDTO carInputDTO);

	ResponseModel getCarBySerialNumber(String serialNumber);

	ResponseModel getOwnerCars(String email);

	ResponseModel getOwnerCarBySerialNumber(String email, String serialNumber);

	ResponseModel getOwnerBookedPeriodsBySerialNumber(String email,String serialNumber);

	ResponseModel getLatestComments();

	ResponseModel addComment(String email,String serialNumber, CommentInputDTO commentInputDTO);

	ResponseModel getThreeLastCommentsOfCarBySerialNumber(String serialNumber);

	ResponseModel getBestBooked();

//	ResponseModel searchCar(String city, LocalDateTime startDateTime, LocalDateTime endDateTime
//	,int minAmount, int maxAmount);

	ResponseModel searchCarByFilters(String make, String modal, String year, String engine, String fuel,
									 String gear, String wheelsDrive);
}
