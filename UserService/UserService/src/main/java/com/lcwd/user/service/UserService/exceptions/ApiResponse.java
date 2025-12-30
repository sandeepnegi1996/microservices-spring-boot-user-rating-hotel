package com.lcwd.user.service.UserService.exceptions;

import lombok.*;
import org.springframework.http.HttpStatus;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ApiResponse {

    private  String message;
    private boolean success;
    private HttpStatus httpStatus;



}
