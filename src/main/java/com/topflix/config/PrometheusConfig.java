package com.topflix.config;

import io.prometheus.client.Counter;
import io.prometheus.client.Gauge;
import io.prometheus.client.exporter.HTTPServer;
import io.prometheus.client.hotspot.DefaultExports;

import java.io.IOException;

/**
 * PrometheusConfig는 메트릭 수집을 위해 Prometheus HTTP 서버를 초기화하고 시작합니다.
 */
public class PrometheusConfig {
    private static final Counter requests = Counter.build()
            .name("requests_total").help("Total requests.").register();
    // 크롤링한 페이지 수를 기록하는 카운터입니다.
    private static final Counter crawledPages = Counter.build()
            .name("crawled_pages_total").help("Total number of pages crawled.").register();

    // 크롤링 작업의 성공/실패 횟수를 기록하는 카운터입니다.
    private static final Counter crawlSuccesses = Counter.build()
            .name("successful_crawls_total").help("Total number of successful crawl operations.").register();
    private static final Counter crawlFailures = Counter.build()
            .name("failed_crawls_total").help("Total number of failed crawl operations.").register();

    // 크롤러 상태에 대한 게이지 측정항목 정의합니다.(1 = 실행 중, 0 = 중지됨)
    private static final Gauge crawlerStatus = Gauge.build()
            .name("crawler_status").help("Crawler status (1 = running, 0 = stopped)").register();

    // 포트 9090에서 Prometheus HTTP 서버를 시작합니다.
    public static void start() throws IOException {
        DefaultExports.initialize();
        //HTTPServer server = new HTTPServer(9090);
    }

    // 총 요청 카운터를 증가시킵니다.
    public static void incrementRequests() {
        requests.inc();
    }

    // 크롤링한 페이지 수를 증가시킵니다.
    public static void incrementCrawledPages() {
        crawledPages.inc();
    }

    // 성공적인 크롤링 작업을 기록합니다.
    public static void incrementCrawlSuccess() {
        crawlSuccesses.inc();
    }

    // 실패한 크롤링 작업을 기록합니다.
    public static void incrementCrawlFailure() {
        crawlFailures.inc();
    }

    // 크롤러 상태를 설정합니다.
    public static void setCrawlerStatus(boolean isRunning) {
        if (isRunning) {
            crawlerStatus.set(1);
        } else {
            crawlerStatus.set(0);
        }
    }
}
