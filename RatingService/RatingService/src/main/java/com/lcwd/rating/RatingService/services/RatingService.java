package com.lcwd.rating.RatingService.services;

import com.lcwd.rating.RatingService.entities.Rating;

import java.util.List;

public interface RatingService {

    //CRUD
    // create

    Rating saveRating(Rating rating);

    // get all rating
    List<Rating> getAllRatings();

    // get Rating by user id

     List<Rating> getRatingByUserId(String userId);

    // get rating by hotelId

    List<Rating> getRatingByHotelId(String hotelId);
}
