package com.lcwd.user.service.UserService.service.impl;

import com.lcwd.user.service.UserService.entities.Hotel;
import com.lcwd.user.service.UserService.entities.Rating;
import com.lcwd.user.service.UserService.entities.User;
import com.lcwd.user.service.UserService.exceptions.ResourceNotFoundException;
import com.lcwd.user.service.UserService.repository.UserRepository;
import com.lcwd.user.service.UserService.service.UserService;
import com.lcwd.user.service.UserService.service.external.HotelExternalService;
import com.lcwd.user.service.UserService.service.external.RatingExternalService;
import com.lcwd.user.service.UserService.service.feignclient.HotelFeignClient;
import com.lcwd.user.service.UserService.service.feignclient.RatingFeignClient;
import com.lcwd.user.service.UserService.service.feignclient.RatingFeignClientFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    private final RestTemplate restTemplate;
    private final RatingExternalService ratingExternalService;
    private final HotelExternalService hotelExternalService;

    private final RatingFeignClient ratingFeignClient;
    private final HotelFeignClient hotelFeignClient;

    private final RatingFeignClientFacade ratingFeignClientFacade;

    @Override
    public User saveUser(User user) {
        String randomUserID =  UUID.randomUUID().toString();
        user.setUserId(randomUserID);
        return userRepository.save(user);

    }

    @Override
    public List<User> getAllUsers() {

        List<User> users = userRepository.findAll();



       List<User> userList = users.stream().map(user -> {
            String userId = user.getUserId();
            List<Rating> ratings = ratingExternalService.getRatingListByUserId(userId);
            user.setRating(ratings);
            return user;
       }).collect(Collectors.toList());


        return userList;
    }

    @Override
    public User getUser(String userId) {
        User user =userRepository.findById(userId).orElseThrow( () ->new ResourceNotFoundException("User is not found "+userId));

        // using rest tempkate
//        user.setRating(ratingExternalService.getRatingListByUserId(userId));
//        log.info("Using feign client to make the api call with eureka ");
        // using feign client
        try {
            user.setRating(ratingFeignClientFacade.getRatingByUserIdFeignClient(userId).get());
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }


        /*
        *  // rating -> hotelid
            // call hotel Service and get hotel details
            // add those to the user service and then return back */


        List<Rating> ratings =  user.getRating();
        for (Rating rating: ratings) {
           String hotelId =  rating.getHotelId();

           // using the restTemplate getForEntity to call the external service
//           Hotel hotel =  hotelExternalService.getHotelById(hotelId);

            // calling the hotel service using feign with hardcoded url
           Hotel hotel =  hotelFeignClient.getHotelById(hotelId);
           rating.setHotel(hotel);
        }


        return user;
    }

    @Override
    public void deleteUser(String userId) {
        userRepository.deleteById(userId);

    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }
}
