package com.topflix.scheduler.job;

import com.topflix.crawler.Crawler;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 CrawlerJob은 영화 크롤링 프로세스를 실행하는 Quartz Job입니다.
 **/
public class CrawlerJob implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        Crawler crawler = new Crawler();
        crawler.fetchMovies();
    }
}
