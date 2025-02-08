package com.lth.identify_service.controller;

import com.lth.identify_service.dto.request.UserCreationRequest;
import com.lth.identify_service.dto.request.UserUpdateRequest;
import com.lth.identify_service.dto.response.ApiResponse;
import com.lth.identify_service.entity.User;
import com.lth.identify_service.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/users")
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    ApiResponse<User> createUser(@RequestBody @Valid UserCreationRequest request) {
        ApiResponse response = new ApiResponse(
            201,
            "User created successfully",
            userService.createUser(request)
        );

        return response;
    }

    @GetMapping
    List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{userId}")
    User getUser(@PathVariable("userId") String userId) {
        return userService.getUser(userId);
    }

    @PutMapping("/{userId}")
    User updateUser(@PathVariable("userId") String userId, @RequestBody UserUpdateRequest request) {
        return userService.updateUser(userId, request);
    }

    @DeleteMapping("/{userId}")
    String deleteUser(@PathVariable("userId") String userId) {
        return userService.deleteUser(userId);
    }

}
