package com.jamp.bt.rest.api.service.api;

import com.jamp.bt.rest.api.dto.user.User;

import java.util.List;

public interface UserService {

    User createUser(User user);
    User updateUser(User user);
    void deleteUser(long id);
    User findById(long id);
    List<User> getAllUsers();
}
