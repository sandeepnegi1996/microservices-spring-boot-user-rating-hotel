package com.lcwd.user.service.UserService.service.impl;

import com.lcwd.user.service.UserService.entities.Hotel;
import com.lcwd.user.service.UserService.entities.Rating;
import com.lcwd.user.service.UserService.entities.User;
import com.lcwd.user.service.UserService.exceptions.ResourceNotFoundException;
import com.lcwd.user.service.UserService.repository.UserRepository;
import com.lcwd.user.service.UserService.service.UserService;
import com.lcwd.user.service.UserService.service.restTemplateExternal.HotelExternalService;
import com.lcwd.user.service.UserService.service.restTemplateExternal.RatingExternalService;
import com.lcwd.user.service.UserService.service.feignclient.HotelFeignClient;
import com.lcwd.user.service.UserService.service.feignclient.RatingFeignClient;
import com.lcwd.user.service.UserService.service.feignclient.RatingFeignClientFacade;
import com.lcwd.user.service.UserService.service.webclientExternal.HotelWebClientExternalService;
import com.lcwd.user.service.UserService.service.webclientExternal.RatingWebClientExternalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Mono;

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
    private final HotelWebClientExternalService hotelWebClientExternalService;

    private final RatingFeignClientFacade ratingFeignClientFacade;

    private final RatingWebClientExternalService ratingWebClientExternalService;

    @Override
    public User saveUser(User user) {
        String randomUserID = UUID.randomUUID().toString();
        user.setUserId(randomUserID);
        return userRepository.save(user);

    }

    @Override
    public List<User> getAllUsers() {

        List<User> users = userRepository.findAll();

        List<User> userList = users.stream().map(user -> {
            log.info("current user used for the raring list by user id {}", user);
            String userId = user.getUserId();
            List<Rating> ratings = ratingExternalService.getRatingListByUserId(userId);
            user.setRating(ratings);
            return user;
        }).collect(Collectors.toList());

        return userList;
    }

    @Override
    public User getUser(String userId) throws ExecutionException, InterruptedException {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User is not found " + userId));

        // using rest tempkate
        // user.setRating(ratingExternalService.getRatingListByUserId(userId));
        // log.info("Using feign client to make the api call with eureka ");
        // using feign client

//            user.setRating(ratingFeignClientFacade.getRatingByUserIdFeignClient(userId).get());


            // letting use the webclient to call the rating service
            //  lets set the userRating after wards
            // here we have not actually procossed the pipeline
            // we have just defined the pipeline

            Mono<List<Rating>> listOfRating = ratingWebClientExternalService.getRatingListByUserIdAsync(userId);

            List<Rating> ratingList = listOfRating.block();

            user.setRating(ratingList);



        /*
         * // rating -> hotelid
         * // call hotel Service and get hotel details
         * // add those to the user service and then return back
         */

        List<Rating> ratings = user.getRating();

        for (Rating rating : ratings) {
            String hotelId = rating.getHotelId();
            log.info("current hotelid used to get the hotel details : {} ", hotelId);

            // using the restTemplate getForEntity to call the external service
//            Hotel hotel = hotelExternalService.getHotelById(hotelId);
            // using webclient to call the hotel service
//            Hotel hotel =  hotelWebClientExternalService.getHotelByIdWebClientSync(hotelId);

            // using webclient with async hotel service call
            Mono<Hotel> hotelResponse  = hotelWebClientExternalService.getHotelByIdWebClientAsync(hotelId);
            // calling the hotel service using feign with hardcoded url
            // Hotel hotel = hotelFeignClient.getHotelById(hotelId);

            log.info("inside the  hotel id : {} ", hotelId);
            rating.setHotel(hotelResponse.block()); // here we are actually calling the pipeline
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
