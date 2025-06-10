package com.cinemate.backend.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DailySummaryScheduler {

    @Scheduled(cron = "0 0 8 * * *")
    public void sendDailySummary() {

        log.info("Daily summary task executed - sending report...");

    }
}