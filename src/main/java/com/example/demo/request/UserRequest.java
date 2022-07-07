package com.example.demo.request;

import com.example.demo.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    private String username;
    private String password;
    private String fullName;
    private String role;
    private String phone;

}
