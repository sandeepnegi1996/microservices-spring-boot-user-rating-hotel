package com.lcwd.user.service.UserService.service;

import com.lcwd.user.service.UserService.entities.User;


import java.util.List;

public interface UserService {

//    CRUD

    User saveUser(User user);

    List<User> getAllUsers();

    User getUser(String userId);

//    TODO :delete
//    TODO : update


    void deleteUser(String userId);

    User updateUser(User user);


}
