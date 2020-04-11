package com.car.rentservice.service;

import com.car.rentservice.dto.*;

public interface ilCarroService {
	UserSuccessResponseDTO updateUser(String email, UpdateUserInputDTO updateUserInputDTO);

	String deleteUser(String email);

	CarOwnerOutputDTO addCar(String email, CarInputDTO carInputDTO);

	ResponseModel deleteCar(String email, String serialNumber);

	ResponseModel updateCar(String email, String serialNumber, CarInputDTO carInputDTO);

	ResponseModel getCarBySerialNumber(String serialNumber);

	ResponseModel getOwnerCars(String email);

	ResponseModel getOwnerCarBySerialNumber(String email, String serialNumber);

	ResponseModel getOwnerBookedPeriodsBySerialNumber(String email,String serialNumber);

	ResponseModel getLatestComments();

	ResponseModel addComment(String email,String serialNumber, CommentInputDTO commentInputDTO);
}
