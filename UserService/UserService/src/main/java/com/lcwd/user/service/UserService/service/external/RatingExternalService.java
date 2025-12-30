package com.lcwd.user.service.UserService.service.external;

import com.lcwd.user.service.UserService.entities.Rating;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RatingExternalService {

    private final RestTemplate restTemplate;



    public List<Rating> getRatingListByUserId(String userId) {
        String url = "http://localhost:8083/rating/users/"+userId;
        log.info("url is  : {} ",url);
        Rating[] ratings = restTemplate.getForObject(url,Rating[].class);
        log.info("list of ratings {} ",ratings.toString());
        return Arrays.asList(ratings);
    }
}
