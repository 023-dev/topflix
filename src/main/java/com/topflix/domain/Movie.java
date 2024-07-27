package com.topflix.domain;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
    private String movieRank;// 영화 순위
    private String movieHref;// 영화 상세 주소
    private String movieImage;// 영화 홍보 사진
    private String movieRating;// 영화 관람가 등급
    private String movieTitle;// 영화 제목
    private String movieTitleEng;// 영화 영어 제목
    private String movieReservationRate;// 영화 예매율
    private String movieEggGage;// 영화 흥행률
    private String movieReleaseDate;// 영화 개봉일
    private String movieDday;// 영화 개봉 잔여일
    private String movieDirector;//영화 감독
    private String movieRunningtime;//영화 상영 시간
    private String movieActor;//영화 배우
    private String movieGenre;//영화 장르
    private String movieStory;//영화줄거리
    private String movieStillCut;//영화 스틸 컷
}
