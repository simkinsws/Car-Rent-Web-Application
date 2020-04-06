package com.car.rentservice.controllers;

import com.car.rentservice.auth.JwtTokenUtil;
import com.car.rentservice.constants.Constants;
import com.car.rentservice.dto.*;
import com.car.rentservice.service.AuthenticationService;
import com.car.rentservice.service.JwtTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenService jwtTokenService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private AuthenticationService authenticationService;

	@PostMapping("/registration")
	public ResponseModel register(@RequestBody RegisterUserInputDTO registerUserInputDTO) {
		ResponseModel responseModel = new ResponseModel();
		List<Object> dataList = new ArrayList<>();
		UserSuccessResponseDTO userSuccessResponseDTO = authenticationService.register(registerUserInputDTO);
		if (userSuccessResponseDTO == null) {
			responseModel.setStatus(Constants.CONFLICT_CODE);
		} else {
			responseModel.setStatus(Constants.SUCCESS_CODE);
		}
		dataList.add(userSuccessResponseDTO);
		responseModel.setDataList(dataList);
		return responseModel;
	}

	@PostMapping("/login")
	public ResponseEntity<?> authentication(@RequestBody JwtRequest jwtRequest) throws Exception {
		authenticate(jwtRequest.getEmail(), jwtRequest.getPassword());
		final UserDetails userDetails = jwtTokenService.loadUserByUsername(jwtRequest.getEmail());

		final String token = jwtTokenUtil.generateToken(userDetails);
		JwtResponse response = null;
		String status;
		if (token != null && !token.isEmpty()) {
			status = "true";
		} else {
			status = "false";
		}
		response = new JwtResponse(token, status);
		return ResponseEntity.ok(response);
	}

	private void authenticate(String email, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

}
