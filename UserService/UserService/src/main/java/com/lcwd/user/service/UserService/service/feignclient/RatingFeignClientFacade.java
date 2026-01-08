package com.lcwd.user.service.UserService.service.feignclient;

import com.lcwd.user.service.UserService.entities.Rating;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

// this layer is similar to what we have like RatingExternalService
// we will use the RateLimiter with the
// now we 
@Service
@RequiredArgsConstructor
@Slf4j
public class RatingFeignClientFacade {

    private final RatingFeignClient ratingFeignClient;
    @Autowired
    private  Executor ratingExecutor;

/*    @CircuitBreaker(name = "ratingCircuitBreaker",fallbackMethod = "ratingCircuitBreakerFallback")
    @Retry(name = "ratingRetryInstance")
    @TimeLimiter(name = "ratingTimeLimiter")
    public List<Rating> getRatingByUserIdFeignClient(String userId) {

        return ratingFeignClient.getRatingByUserId(userId);

    }*/

    @CircuitBreaker(name = "ratingCircuitBreaker",fallbackMethod = "ratingCircuitBreakerFallback")
    @Retry(name = "ratingRetryInstance")
    @TimeLimiter(name = "ratingTimeLimiter")
    public CompletableFuture<List<Rating>> getRatingByUserIdFeignClient(String userId) {
        // here we have created the thread pool task executor bean with the pool size and we have passed the ratingExecutor to the
        // completable future  so this it will use the threads from that pool
        return CompletableFuture.supplyAsync(() -> ratingFeignClient.getRatingByUserId(userId),ratingExecutor);

    }

    public CompletableFuture<List<Rating>> ratingCircuitBreakerFallback(String userId, Exception ex) {

        log.info("fallback for the rating circuit breaker is executed ");

        Rating r1 = Rating.builder().ratingId("28f14a32-060f-4b00-b6c9-45f09700ad46").rating(2).feedback("dummy hotel rating ").hotelId("4ea5c0d1-10ef-497c-9c06-58f5687f6bbe").build();

        return CompletableFuture.completedFuture(Collections.singletonList(r1));
    }
}
