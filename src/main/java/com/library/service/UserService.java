package com.library.service;

import com.library.dto.UserDTO;
import com.library.exception.DataInsertionException;
import com.library.exception.UsernameAlreadyExistsException;
import com.library.mapper.UserMapper;
import com.library.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;

    @Transactional
    public void register(UserDTO userDTO) {

        if (isUsernameExists(userDTO.getUsername())) {
            throw new UsernameAlreadyExistsException(userDTO.getUsername());
        }

        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());

        try {
            int isInserted = userMapper.insertUser(user);
            if (isInserted == 0) {
                throw new DataInsertionException("Fail to insert user data: " + userDTO.getUsername());
            }
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error occurred during user registration: " + e.getMessage());
        }

    }

    private boolean isUsernameExists(String username) {
        return userMapper.countUsername(username) > 0;
    }
}
