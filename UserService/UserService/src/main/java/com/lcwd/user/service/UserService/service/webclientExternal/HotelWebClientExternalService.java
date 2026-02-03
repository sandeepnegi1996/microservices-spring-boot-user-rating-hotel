package com.lcwd.user.service.UserService.service.webclientExternal;

import com.lcwd.user.service.UserService.entities.Hotel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
@Slf4j
public class HotelWebClientExternalService {

    private final WebClient webClient;

    private String url = "http://localhost:8082/hotel/";

    public Hotel getHotelByIdWebClientSync(String hotelId) {

        log.info("inside hte hotel webclient external service ");


        String finalUrl = url + hotelId;


        Hotel hotelResponse = webClient.get()
                .uri(finalUrl)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError,
                        response -> Mono.error(new RuntimeException("4xx error when calling hotel service")))
                .onStatus(HttpStatusCode::is5xxServerError,
                        response -> Mono.error(new RuntimeException("5xx error when calling hotel service")))

                .bodyToMono(Hotel.class)
                .block();


        log.info("response from hotel service using webclient : {} ", hotelResponse);
        return hotelResponse;

    }



    //async
    public Mono<Hotel> getHotelByIdWebClientAsync(String hotelId) throws ExecutionException, InterruptedException {

        log.info("inside the hotel webclient external service async for hotel id : {} ", hotelId);

       return webClient.get()
                .uri(url + hotelId)
                .retrieve()
                .bodyToMono(Hotel.class);

    }

}
