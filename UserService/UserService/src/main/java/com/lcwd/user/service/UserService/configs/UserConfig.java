package com.lcwd.user.service.UserService.configs;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class UserConfig {


    /*
    * If data is flowing: As long as a new packet is received at least once every 3 seconds,
    *  the connection will not time out, even if the total transfer takes much longer than 3 seconds.
If data stops: If there is a pause in the data stream (e.g., the server is processing a large batch or the network hangs)
*  that exceeds 3 seconds, the client will throw an exception. */
    @Bean()
    @LoadBalanced
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.connectTimeout(Duration.ofSeconds(3))
                .readTimeout(Duration.ofSeconds(3)).build();
    }
}
