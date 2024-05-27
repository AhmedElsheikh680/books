package com.spring.service;


import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PriceSchedule {

    Logger log = LoggerFactory.getLogger(PriceSchedule.class);

//    @Scheduled(initialDelay = 2000, fixedRate = 2000)
//    @Scheduled(initialDelay = 2000, fixedRateString = "PT02S")
//    @Scheduled(initialDelay = 2000, fixedRateString = "${price.interval}")
//    @Scheduled(cron = "${interval-in-cron}")
    @Scheduled(fixedRate = 2000)
    @SchedulerLock(name="BookComputedPrice")
    @Async
    public void computePrice() throws InterruptedException {
        Thread.sleep(9000);
        log.info("Compute Price: "+ LocalDateTime.now());
    }

    @Scheduled(fixedRate = 2000)
    @SchedulerLock(name = "BookComputedDiscount")
    @Async
    public void computeDiscount() throws InterruptedException {
        Thread.sleep(9000);
        log.info("Compute Discount: "+ LocalDateTime.now());
    }
}
