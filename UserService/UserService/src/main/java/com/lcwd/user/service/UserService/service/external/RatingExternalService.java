package com.lcwd.user.service.UserService.service.external;

import com.lcwd.user.service.UserService.entities.Rating;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RatingExternalService {

    private final RestTemplate restTemplate;


//    @CircuitBreaker(name = "ratingCircuitBreaker",fallbackMethod = "ratingCircuitBreakerFallback")
//    @Retry(name = "ratingRetryInstance")
    public List<Rating> getRatingListByUserId(String userId) {

        // TODO jere we are caling with the rating service since we are using the api
        // gateway to call our service by name
        // we can also call the rating service with the help of direct url
        String url = "http://RATINGSERVICE/rating/users/" + userId;
        // String url = "http://localhost:8083/rating/users/"+userId;

        log.info("url is  : {} ", url);
        ResponseEntity<Rating[]> responseEntity = restTemplate.getForEntity(url, Rating[].class);

        log.info("calling rating service response code : {} ", responseEntity.getStatusCode());

        return Arrays.asList(responseEntity.getBody());
    }

    public List<Rating> ratingCircuitBreakerFallback(String userId, Exception ex) {

        log.info("fallback for the rating circuit breaker is executed ");

        Rating r1 = Rating.builder().ratingId("28f14a32-060f-4b00-b6c9-45f09700ad46").rating(2)
                .feedback("dummy hotel rating ").hotelId("4ea5c0d1-10ef-497c-9c06-58f5687f6bbe").build();

        return Collections.singletonList(r1);
    }
}
