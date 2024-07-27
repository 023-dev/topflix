package com.topflix.crawler;

import com.topflix.config.LogbackConfig;
import com.topflix.config.PrometheusConfig;
import com.topflix.domain.Movie;
import com.topflix.repository.MovieRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Crawler는 CGV 영화 웹사이트를 크롤링하여 영화 정보를 추출하는 역할을 합니다.
 */
public class Crawler {
    /**
     * CGV 웹사이트에서 영화를 가져옵니다.
     */
    final static String URL = "http://www.cgv.co.kr/movies";
    public void fetchMovies() {
        List<Movie> movies = new ArrayList<Movie>();
        /* 크롤링 시작 로그를 기록합니다.
         * 2024-07-19 15:32:45 INFO  com.topflix.crawler.CgvCrawler - 크롤링 시작
         */
        PrometheusConfig.incrementCrawledPages();
        LogbackConfig.logInfo("크롤링 시작");
        try {
            // CGV 웹사이트에 연결하여 문서를 가져옵니다.
            Document document = Jsoup.connect(URL).get();
            Elements ols = document.getElementsByTag("ol");
            // 영화 요소를 반복하여 각 영화를 처리합니다.
            for (Element ol : ols) {
                Elements lis = ol.getElementsByTag("li");
                for (Element li : lis) {
                    if(li.text() != "") {
                        Movie movie = new Movie();
                        movie.setMovieRank(li.getElementsByClass("rank").text().substring(3));
                        movie.setMovieHref(li.getElementsByTag("a").attr("href"));
                        movie.setMovieImage(li.getElementsByTag("img").attr("src".trim()));
                        movie.setMovieRating(li.getElementsByTag("i").text().trim());
                        movie.setMovieTitle(li.getElementsByClass("title").text().trim());
                        movie.setMovieReservationRate(li.getElementsByClass("percent").first().getElementsByTag("strong").last().text().trim().replace("예매율",""));
                        movie.setMovieEggGage(li.getElementsByClass("egg-gage").last().text().trim());
                        movie.setMovieReleaseDate(li.getElementsByClass("txt-info").text().trim().substring(0,10));
                        movie.setMovieDday(li.getElementsByClass("dday").text().trim());//empty 확인

                        Document detailDocument = Jsoup.connect(URL.substring(0, URL.length()-7).concat(movie.getMovieHref())).get();
                        Elements contents = detailDocument.getElementsByClass("box-contents");
                        movie.setMovieTitleEng(contents.get(0).getElementsByTag("p").first().text().trim());
                        Elements doc = detailDocument.getElementsByClass("spec");
                        movie.setMovieDirector(doc.select("dt:contains(감독) + dd a").text());
                        movie.setMovieActor(doc.select("dt:contains(배우) + dd").text());
                        movie.setMovieGenre(doc.select("dt:contains(장르)").text().replace("장르:&nbsp;", "").replace("&nbsp;", "").replace(";", ", ").replace("장르 : ", ""));
                        movie.setMovieRunningtime(doc.select("dd:contains(분)").text().split(",")[1].trim());
                        movie.setMovieStory(detailDocument.getElementsByClass("sect-story-movie").text().trim());

                        Elements images = detailDocument.getElementsByClass("item");
                        String url = null;
                        for (Element image : images) {
                            url = image.getElementsByTag("img").attr("data-src");
                            try {
                                URL imageUrl = new URL(url);
                                System.out.println(movie.getMovieTitle()+":"+imageUrl);
                                BufferedImage bufferedImage = ImageIO.read(imageUrl);

                                int width = bufferedImage.getWidth();
                                int height = bufferedImage.getHeight();
                                if (width > height){
                                    break;
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        movie.setMovieStillCut(url);
                        /* 영화 데이터를 처리하고 저장합니다.
                         * 2024-07-19 15:32:50 INFO  com.topflix.crawler.CgvCrawler - 영화 제목: Inception, 정보: A mind-bending thriller by Christopher Nolan
                         */
                        LogbackConfig.logInfo("영화 제목: " + movie.getMovieTitle() + ", 정보: " + movie.toString());
                        movies.add(movie);
                        // 크롤링 성공 시 메트릭 업데이트
                        PrometheusConfig.recordCrawlSuccess();
                    }
                }
            }
            /* 크롤링 완료 로그를 기록합니다.
             * 2024-07-19 15:32:55 INFO  com.topflix.crawler.Crawler - 크롤링 완료
             */
            LogbackConfig.logInfo("크롤링 완료");
            new MovieRepository().save(movies);
            LogbackConfig.logInfo("DB 저장");
        } catch (IOException e) {
            /* 크롤링 중 오류 발생 시 오류 로그를 기록합니다.
            * 2024-07-19 15:33:00 ERROR com.topflix.crawler.Crawler - 크롤링 오류
            * java.io.IOException: 오류 메시지 at ... (stack trace)
            */
            PrometheusConfig.recordCrawlFailure();
            LogbackConfig.logError("크롤링 오류", e);
        }
    }

    public static void main(String[] args) throws Exception {
        Crawler crawler = new Crawler();
        crawler.fetchMovies();
    }
}
