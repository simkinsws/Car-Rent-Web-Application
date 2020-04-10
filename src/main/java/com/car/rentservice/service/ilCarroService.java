package com.car.rentservice.service;

import com.car.rentservice.dto.*;

public interface ilCarroService {
	UserSuccessResponseDTO updateUser(String email, UpdateUserInputDTO updateUserInputDTO);

	String deleteUser(String email);

	CarOwnerOutputDTO addCar(String email, CarInputDTO carInputDTO);

	String deleteCar(String email, String serialNumber);

	ResponseModel updateCar(String email, String serialNumber, CarInputDTO carInputDTO);
}
