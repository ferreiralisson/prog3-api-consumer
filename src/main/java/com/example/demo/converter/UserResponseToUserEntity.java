package com.example.demo.converter;

import com.example.demo.dto.UserResponse;
import com.example.demo.model.UserModel;

public class UserResponseToUserEntity {

    public static UserModel convert(UserResponse userResponse) {
        return new UserModel(
                userResponse.id(),
                userResponse.name(),
                userResponse.email()
        );
    }
}
