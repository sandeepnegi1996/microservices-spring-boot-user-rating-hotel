package com.lcwd.user.service.UserService.service.feignclient;

import com.lcwd.user.service.UserService.entities.Rating;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

// this layer is similar to what we have like RatingExternalService
// now we 
@Service
@RequiredArgsConstructor
public class RatingFeignClientFacade {

    private final RatingFeignClient ratingFeignClient;

    @CircuitBreaker(name = "ratingCircuitBreaker",fallbackMethod = "ratingCircuitBreakerFallback")
    @Retry(name = "ratingRetryInstance")
    public List<Rating> getRatingByUserIdFeignClient(String userId) {

        return ratingFeignClient.getRatingByUserId(userId);

    }

    public List<Rating> ratingCircuitBreakerFallback(String userId, Exception ex) {

        log.info("fallback for the rating circuit breaker is executed ");

        Rating r1 = Rating.builder().ratingId("28f14a32-060f-4b00-b6c9-45f09700ad46").rating(2).feedback("dummy hotel rating ").hotelId("4ea5c0d1-10ef-497c-9c06-58f5687f6bbe").build();

        return Collections.singletonList(r1);
    }
}
