package com.lcwd.user.service.UserService.service.external;

import com.lcwd.user.service.UserService.entities.Rating;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
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


        String url = "http://RATINGSERVICE/rating/users/"+userId;


        log.info("url is  : {} ",url);
       ResponseEntity<Rating[]>  responseEntity =  restTemplate.getForEntity(url,Rating[].class);

       log.info("calling rating service response code : {} ",responseEntity.getStatusCode());

        return Arrays.asList(responseEntity.getBody());
    }
}
