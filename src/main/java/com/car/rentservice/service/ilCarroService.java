package com.car.rentservice.service;

import com.car.rentservice.dto.UpdateUserInputDTO;
import com.car.rentservice.dto.UserSuccessResponseDTO;

public interface ilCarroService {
    UserSuccessResponseDTO updateUser(String email, UpdateUserInputDTO updateUserInputDTO);
    String deleteUser(String email);
}
