package com.car.rentservice.service;

import com.car.rentservice.dto.*;

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
}
