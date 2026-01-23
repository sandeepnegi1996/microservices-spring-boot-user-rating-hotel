package com.lcwd.rating.RatingService.controllers;

import com.lcwd.rating.RatingService.entities.Rating;
import com.lcwd.rating.RatingService.services.RatingService;
import com.lcwd.rating.RatingService.services.RatingServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rating")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @PostMapping
    public ResponseEntity<Rating> create(@RequestBody Rating rating) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ratingService.saveRating(rating));
    }

    @GetMapping
    public ResponseEntity<List<Rating>> getAllRatings() throws InterruptedException {
        return ResponseEntity.status(HttpStatus.OK).body(ratingService.getAllRatings());
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Rating>> getAllRatingByUserId(@PathVariable String userId){
        return ResponseEntity.status(HttpStatus.OK).body(ratingService.getRatingByUserId(userId));
    }


    @GetMapping("/hotels/{userId}")
    public ResponseEntity<List<Rating>> getAllRatingByHotelId(@PathVariable String hotelId){
        return ResponseEntity.status(HttpStatus.OK).body(ratingService.getRatingByHotelId(hotelId));
    }

}
