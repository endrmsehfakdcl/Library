package com.library.mapper;

import com.library.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    int insertUser(User user);
    int countUsername(String username);

}
