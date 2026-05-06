package com.sperandio.lukas.NewToDoList.service;

import com.sperandio.lukas.NewToDoList.dto.request.UserRequest;
import com.sperandio.lukas.NewToDoList.dto.response.UserResponse;
import com.sperandio.lukas.NewToDoList.model.User;
import com.sperandio.lukas.NewToDoList.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserResponse> getAllUsers(){
        return userRepository.findAll()
                .stream()
                .map(u -> new UserResponse(u.getId(), u.getUsername(), u.getEmail(), u.getFirstName(), u.getLastName()))
                .toList();
    }

    public UserResponse addUser(UserRequest userRequest){
        User u = new  User();
        u.setUsername(userRequest.username());
        u.setPassword(userRequest.password());
        u.setEmail(userRequest.email());
        u.setFirstName(userRequest.firstName());
        u.setLastName(userRequest.lastName());
        User userSaved = userRepository.save(u);
        return new UserResponse(
                userSaved.getId(),
                userSaved.getUsername(),
                userSaved.getEmail(),
                userSaved.getFirstName(),
                userSaved.getLastName()
        );
    }

    public UserResponse getById(long id){
        return userRepository.findById(id)
                .map(u -> new UserResponse(u.getId(), u.getUsername(), u.getEmail(), u.getFirstName(), u.getLastName()))
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public UserResponse updateUser(long id, UserRequest userRequest){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));;

        user.setUsername(userRequest.username());
        user.setEmail(userRequest.email());
        user.setPassword(userRequest.password());
        user.setFirstName(userRequest.firstName());
        user.setLastName(userRequest.lastName());
        UserResponse userResponse = new UserResponse(user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName());
        userRepository.save(user);
        return userResponse;
    }

    public void delete(long id){
        userRepository.deleteById(id);
    }
}
