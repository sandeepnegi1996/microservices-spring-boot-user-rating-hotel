package com.lcwd.rating.RatingService.repositories;

import com.lcwd.rating.RatingService.entities.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating,String> {

    /*Custom finder Method */


    List<Rating> findByUserId(String userId);

    List<Rating> findByHotelId(String hotelId);


}
