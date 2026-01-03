package com.example.lcwd.hotel.HotelService.services;

import com.example.lcwd.hotel.HotelService.entities.Hotel;

import java.util.List;

public interface HotelService {

    Hotel create(Hotel hotel);

    List<Hotel> getAllHotel();

    Hotel getHotelById(String id);

}
