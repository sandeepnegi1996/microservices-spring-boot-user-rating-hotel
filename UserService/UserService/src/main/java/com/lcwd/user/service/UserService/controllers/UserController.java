package com.lcwd.user.service.UserService.controllers;

import com.lcwd.user.service.UserService.entities.User;
import com.lcwd.user.service.UserService.service.UserService;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        User user1 = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }

    /* simple implementation of rate limiter */
    @GetMapping
    // @RateLimiter(name = "getAllUsersRateLimiter", fallbackMethod =
    // "getAllUsersFallBackMethod")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
    }

    public ResponseEntity<List<User>> getAllUsersFallBackMethod(Exception ex) {

        throw new ResponseStatusException(
                HttpStatus.TOO_MANY_REQUESTS,
                "Too many requests, please try later");
        // return
        // ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(Collections.emptyList());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable String userId) {
        User user = userService.getUser(userId);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @GetMapping("/current-user")
    public String getCurrentUser(Principal principal) {
        return principal.getName();
    }
}
