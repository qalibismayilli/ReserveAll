package com.reserveall.reserveall.service;

import com.reserveall.reserveall.dto.request.UserRequestDto;
import com.reserveall.reserveall.dto.response.UserResponseDto;
import com.reserveall.reserveall.exception.GenericException;
import com.reserveall.reserveall.model.User;
import com.reserveall.reserveall.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public UserResponseDto createUser(UserRequestDto request){
        User user = new User.Builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();
        User fromDb = userRepository.save(user);
        return new UserResponseDto(fromDb.getUsername(), fromDb.getEmail(), fromDb.getRole());
    }

    protected User findUserByUsername(String username){
        return userRepository.getUserByUsername(username)
                .orElseThrow(() -> new GenericException("not found user by username"));
    }

    public UserResponseDto findUserResponseDtoByUsername(String username){
        User user = userRepository.getUserByUsername(username)
                .orElseThrow(() -> new GenericException("not found user by username"));
        return new UserResponseDto(user.getUsername(),user.getEmail(),user.getRole());
    }


}
