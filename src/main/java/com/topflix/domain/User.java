package com.topflix.domain;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    private String email;
    private String password;
    private Date birthday;
    private String name;
    private String phone;
}