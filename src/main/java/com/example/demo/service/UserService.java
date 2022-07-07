package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.request.UserRequest;

public interface UserService {

    User findByPasswordAndUsername(String username, String password);

    User findByUsername(String username);

    User addUser(UserRequest userRequest);
}
