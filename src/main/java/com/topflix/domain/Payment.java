package com.topflix.domain;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    private String pgToken;
    private String date;
    private String paymentType;
    private int amount;
    private String userEmail;
    private String movieTitle;
}