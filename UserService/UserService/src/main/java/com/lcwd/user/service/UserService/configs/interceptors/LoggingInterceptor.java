package com.lcwd.user.service.UserService.configs.interceptors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

@Slf4j
public class LoggingInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
            throws IOException {

        long start = System.currentTimeMillis();

        // before execution if we wanted to do something

        ClientHttpResponse response = execution.execute(request, body);

        // after execution we wanted to do something
        long time = System.currentTimeMillis() - start;

        log.info("RestTemplate call => method: {} URI: {} status: {} in {}ms", request.getMethod(), request.getURI(),
                response.getStatusCode(), time);

        return response;
    }
}
