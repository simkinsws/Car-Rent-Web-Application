package com.car.rentservice.controllers;

import com.car.rentservice.dto.UpdateUserInputDTO;
import com.car.rentservice.dto.UserSuccessResponseDTO;
import com.car.rentservice.service.ilCarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ilCarroController {
    @Autowired
    private ilCarroService ilCarroService;

    @DeleteMapping("/user")
    public void deleteUser(Authentication authentication) {
        ilCarroService.deleteUser(authentication.getName());
    }

    @PutMapping("/user")
    public UserSuccessResponseDTO updateUser(@RequestBody UpdateUserInputDTO userInputDTO,
                                             Authentication authentication) {
        return ilCarroService.updateUser(authentication.getName(), userInputDTO);
    }

}