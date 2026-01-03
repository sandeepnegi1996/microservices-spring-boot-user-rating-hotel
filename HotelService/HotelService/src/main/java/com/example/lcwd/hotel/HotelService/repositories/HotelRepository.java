package com.example.lcwd.hotel.HotelService.repositories;

import com.example.lcwd.hotel.HotelService.entities.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel,String> {
}
