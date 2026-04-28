package com.company.entity;

import com.company.enums.HomeType;
import com.company.enums.PostType;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Post implements Serializable {

    private String id;
    private String userId;
    private HomeType homeType;
    private Address address;
    private double field;
    private int roomCount;
    private long price;
    private PostType postType;
    private String description;

}
