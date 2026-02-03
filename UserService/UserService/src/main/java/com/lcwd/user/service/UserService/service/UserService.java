package com.lcwd.user.service.UserService.service;

import com.lcwd.user.service.UserService.entities.User;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface UserService {

    // CRUD

    User saveUser(User user);

    List<User> getAllUsers();

    User getUser(String userId) throws ExecutionException, InterruptedException;

    // TODO :delete
    // TODO : update

    void deleteUser(String userId);

    User updateUser(User user);

}
