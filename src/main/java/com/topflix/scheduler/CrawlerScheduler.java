package com.topflix.scheduler;

import com.topflix.scheduler.job.CrawlerJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 CrawlerScheduler는 Quartz를 사용하여 CrawlerJob을 예약하는 역할을 담당합니다.
 **/
public class CrawlerScheduler {
    public void start() throws SchedulerException {
        // 작업을 정의하고 이를 CrawlerJob 클래스에 연결합니다.
        JobDetail job = JobBuilder.newJob(CrawlerJob.class)
                .withIdentity("CrawlerJob")
                .build();

        // 지금 작업을 실행한 다음 매시간 실행하도록 트리거합니다.
        Trigger trigger = TriggerBuilder.newTrigger()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInHours(1)
                        .repeatForever())
                .build();

        // 새 스케줄러 만들기
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.start();
        scheduler.scheduleJob(job, trigger);
    }
}
