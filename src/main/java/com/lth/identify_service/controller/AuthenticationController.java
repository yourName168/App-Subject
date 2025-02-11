package com.lth.identify_service.controller;

import com.lth.identify_service.dto.request.AuthenticationRequest;
import com.lth.identify_service.dto.response.ApiResponse;
import com.lth.identify_service.dto.response.AuthenticationResponse;
import com.lth.identify_service.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
    AuthenticationService authenticationService;

    @PostMapping("/login")
    ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        AuthenticationResponse authResponse = new AuthenticationResponse(authenticationService.authenticate(request));
        ApiResponse response = new ApiResponse(200, "Authentication successful", authResponse);
        return response;
    }
}
