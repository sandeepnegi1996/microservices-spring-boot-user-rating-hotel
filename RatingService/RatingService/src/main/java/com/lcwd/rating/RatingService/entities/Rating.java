package com.lcwd.rating.RatingService.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ratings",schema = "rating-db")
@Builder
public class Rating {

    @Id
    private String ratingId;
    private int rating;
    private String feedback;
    private String userId;
    private String hotelId;
}
