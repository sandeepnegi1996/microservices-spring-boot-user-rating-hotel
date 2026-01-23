package com.lcwd.user.service.UserService.service.external;


import com.lcwd.user.service.UserService.entities.Hotel;
import com.lcwd.user.service.UserService.entities.Rating;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class HotelExternalService {
    private final RestTemplate restTemplate;


    @CircuitBreaker(name = "hotelCircuitBreaker",fallbackMethod = "hotelCircuitBreakerFallback")
    @Retry(name = "hotelRetryInstance")
    public Hotel getHotelById(String hotelId) {
//        String url = "http://HOTELSERVICE/hotel/"+hotelId;
        String url ="http://localhost:8082/hotel/" + hotelId;

        log.info("url for hotel service   : {} ",url);
//        Hotel hotel = restTemplate.getForObject(url,Hotel.class);
       ResponseEntity<Hotel> hotelResponseEntity = restTemplate.getForEntity(url,Hotel.class);
       log.info("response status code for hotel external service for hotelId : {} code is : {}",hotelId,hotelResponseEntity.getStatusCode());
        log.info("external service call finished for the hotel :{} ",hotelResponseEntity.getBody());
        return hotelResponseEntity.getBody();
    }

    public Hotel hotelCircuitBreakerFallback(String hotelId, Exception ex) {

        log.info("fallback for the hotel  circuit breaker is executed ");

       Hotel hotel =     Hotel.builder().id("dummy-hotel-id").about("dummy hotel abouit ").location("delhi").build();

        return hotel;
    }
}

