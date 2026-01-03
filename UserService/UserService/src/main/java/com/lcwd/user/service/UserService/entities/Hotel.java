package com.lcwd.user.service.UserService.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Hotel {


    private String id;
    private String name;
    private String location;
    private String about;
}
