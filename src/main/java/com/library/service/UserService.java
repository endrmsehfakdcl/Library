package com.library.service;

import com.library.dto.UserDTO;
import com.library.mapper.UserMapper;
import com.library.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;

    public void register(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());

        userMapper.insertUser(user);
    }
}
