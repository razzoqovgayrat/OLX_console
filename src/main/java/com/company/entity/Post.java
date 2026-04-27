package com.company.entity;

import com.company.enums.HomeType;
import com.company.enums.PostType;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"id"})
public class Post {

    private String id;
    private HomeType homeType;
    private Address address;
    private double field;
    private int roomNumber;
    private long price;
    private PostType postType;
    private String description;

}
