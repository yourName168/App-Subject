package com.lth.identify_service.service;

import com.lth.identify_service.dto.request.UserCreationRequest;
import com.lth.identify_service.dto.request.UserUpdateRequest;
import com.lth.identify_service.dto.response.UserResponse;
import com.lth.identify_service.entity.User;
import com.lth.identify_service.exception.AppException;
import com.lth.identify_service.exception.ErrorCode;
import com.lth.identify_service.mapper.UserMapper;
import com.lth.identify_service.repository.UserRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;

    public UserResponse createUser(UserCreationRequest request) {
        String userName = request.getUsername();
        if (userRepository.existsByUsername(userName)) {
            throw new AppException(ErrorCode.USER_EXISTS);
        }

        User user = userMapper.toUser(request);
        return userMapper.toUserResponse(userRepository.save(user));

    }

    public List<UserResponse> getUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toUserResponse)
                .toList();
    }

    public UserResponse getUser(String userId) {
        return userMapper.toUserResponse(userRepository.findById(userId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND)));
    }

    public UserResponse updateUser(String userId, UserUpdateRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        userMapper.updateUser(user, request);

        return userMapper.toUserResponse(userRepository.save(user));
    }

    public String deleteUser(String userId) {
        try {
            userRepository.deleteById(userId);
            return "User deleted successfully";
        } catch (Exception e) {
            return "User not found";
        }
    }
}
