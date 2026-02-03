package com.lcwd.user.service.UserService.service.webclientExternal;

import com.lcwd.user.service.UserService.entities.Rating;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class RatingWebClientExternalService {

    private final WebClient webClient;
    public Mono<List<Rating>> getRatingListByUserIdAsync(String userId) {
        // TODO implement the webclient call to rating service to get the ratings by user id
        log.info("inside the rating webclient external service for user id : {} ", userId);

       return  webClient.get()
                .uri("http://localhost:8083/rating/users/" + userId)
                .retrieve()
               .bodyToMono(new ParameterizedTypeReference<List<Rating>>() {});



    }

}
