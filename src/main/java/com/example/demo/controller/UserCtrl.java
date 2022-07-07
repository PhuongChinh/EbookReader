package com.example.demo.controller;


import java.util.Optional;

import com.example.demo.request.UserRequest;
import com.example.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.MessageResp;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;



@RestController
@RequestMapping("/api/v1/userCtrl")
public class UserCtrl {
	static final Logger logger = LoggerFactory.getLogger(UserCtrl.class);
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/login")
	public HttpEntity<Object> getAllUser(@RequestParam String userName,
			@RequestParam String password) {
		User user = userService.findByPasswordAndUsername(password, userName);
		if (user != null) {
			return ResponseEntity.ok(new MessageResp(200, "OK", user));
		} else {
			return ResponseEntity.ok(new MessageResp(200, "FAIL", "Tên đăng nhập hoặc mật khẩu sai!"));
		}
	}

	@PostMapping("/addUser")
	public HttpEntity<Object> addUser(@RequestBody UserRequest userRequest) {
		User user = userService.findByUsername(userRequest.getUsername());
		if (user != null) {
			return ResponseEntity.ok(new MessageResp(403, "ERROR", "Đã tồn tại username"));
		} else {
			User newUser = userService.addUser(userRequest);
			return ResponseEntity.ok(new MessageResp(200, "SUCCESS", newUser));
		}
	}
}
