package com.topflix;

import com.topflix.scheduler.CrawlerScheduler;
import com.topflix.config.PrometheusConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


@WebListener
public class WebAppInitializer implements ServletContextListener {
    private static final Logger logger = LoggerFactory.getLogger(WebAppInitializer.class);
    private static final CrawlerScheduler scheduler = new CrawlerScheduler();
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // 톰캣 시작 시 스케줄러 시작
        try {
            //scheduler.start();
            PrometheusConfig.start();
            logger.info("Scheduler and Prometheus started successfully.");
        } catch (Exception e) {
            logger.error("Error starting scheduler or Prometheus", e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // 톰캣 종료 시 스케줄러 정지 (필요한 경우)
        // Quartz 스케줄러 중지
        if (scheduler != null) {
            scheduler.stop();
        }
    }
}