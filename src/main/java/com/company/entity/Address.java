package com.company.entity;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class Address implements Serializable {

    private String city;
    private String street;
    private int apartNumber;

}
