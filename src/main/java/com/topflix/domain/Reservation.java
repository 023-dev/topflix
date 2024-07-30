package com.topflix.domain;
import lombok.*;
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {
    private String reservationId;
    private String reservationDate;
    private String reservationTheater;
    private String userEmail;
    private String movieTitle;
    private String paymentCode;
}
