package com.topflix.domain;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Wish {
    private int wishSeq;
    private String userEmail;
    private String movieTitle;
}
