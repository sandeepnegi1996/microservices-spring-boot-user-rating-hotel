package com.lcwd.user.service.UserService.service.external;


import com.lcwd.user.service.UserService.entities.Hotel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
@RequiredArgsConstructor
public class HotelExternalService {
    private final RestTemplate restTemplate;


    public Hotel getHotelById(String hotelId) {
        String url = "http://localhost:8082/hotel/"+hotelId;
//        Hotel hotel = restTemplate.getForObject(url,Hotel.class);
       ResponseEntity<Hotel> hotelResponseEntity = restTemplate.getForEntity(url,Hotel.class);
       log.info("response status code for hotel external service for hotelId : {} code is : {}",hotelId,hotelResponseEntity.getStatusCode());
        log.info("external service call finished for the hotel :{} ",hotelResponseEntity.getBody());
        return hotelResponseEntity.getBody();
    }
}

