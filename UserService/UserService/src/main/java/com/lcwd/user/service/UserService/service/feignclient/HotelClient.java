package com.lcwd.user.service.UserService.service.feignclient;

import com.lcwd.user.service.UserService.entities.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/*implementing feign client with the external service call */
@FeignClient(
        name = "hotel-external-client",
        url = "${external.service.url.hotel}"
)
public interface HotelClient {

    /*    @GetMapping("/{hotelId}")
    public ResponseEntity<Hotel> getHotelById(@PathVariable String hotelId) {
        return ResponseEntity.status(HttpStatus.OK).body(hotelService.getHotelById(hotelId));
    }*/

    @GetMapping("/{hotelId}")
    Hotel getHotelById(@PathVariable String hotelId) ;
}
