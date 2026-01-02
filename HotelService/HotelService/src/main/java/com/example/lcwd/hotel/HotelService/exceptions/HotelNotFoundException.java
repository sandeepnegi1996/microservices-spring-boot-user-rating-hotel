package com.example.lcwd.hotel.HotelService.exceptions;

public class HotelNotFoundException extends RuntimeException {

    public HotelNotFoundException(){
        super();
    }

    public HotelNotFoundException(String msg) {
        super(msg);
    }
}
