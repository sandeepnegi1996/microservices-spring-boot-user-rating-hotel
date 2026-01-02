package com.example.lcwd.hotel.HotelService.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(HotelNotFoundException.class)
    public ResponseEntity<ApiResponse> hotelNotFoundException(HotelNotFoundException ex) {
       String message =   ex.getMessage();
       ApiResponse response = ApiResponse.builder().message(message).success(true).httpStatus(HttpStatus.NOT_FOUND).build();
       return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }
}
