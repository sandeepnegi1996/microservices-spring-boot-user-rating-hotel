package com.lcwd.user.service.UserService.service.feignclient;

import com.lcwd.user.service.UserService.entities.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
/*
* feign rating client with eureka service discovery with the name as the service registered on the discovery server */
@FeignClient(
        name = "RATINGSERVICE",
        path = "/rating/users"
)
public interface RatingFeignClient {

    @GetMapping("/{userId}")
    List<Rating> getRatingByUserId(@PathVariable String userId);
}
