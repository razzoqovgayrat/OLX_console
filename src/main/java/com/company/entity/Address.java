package com.company.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class Address {

    private String city;
    private String street;
    private String ApartNumber;

}
