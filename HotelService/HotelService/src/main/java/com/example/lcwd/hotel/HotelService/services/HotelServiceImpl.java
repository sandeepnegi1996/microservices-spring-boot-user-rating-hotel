package com.example.lcwd.hotel.HotelService.services;

import com.example.lcwd.hotel.HotelService.entities.Hotel;
import com.example.lcwd.hotel.HotelService.exceptions.HotelNotFoundException;
import com.example.lcwd.hotel.HotelService.repositories.HotelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;

    @Override
    public Hotel create(Hotel hotel) {
        String uuid = UUID.randomUUID().toString();
        hotel.setId(uuid);
        return hotelRepository.save(hotel);
    }

    @Override
    public List<Hotel> getAllHotel() {
        return  hotelRepository.findAll();
    }

    @Override
    public Hotel getHotelById(String id) {

        return hotelRepository.findById(id).orElseThrow(() ->  new HotelNotFoundException("hotel id not found" + id));
    }
}
