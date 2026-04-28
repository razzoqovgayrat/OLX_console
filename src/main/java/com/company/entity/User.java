package com.company.entity;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    private String id;
    private String fullName;
    private String phoneNumber;
    private String password;
    private String email;

}
