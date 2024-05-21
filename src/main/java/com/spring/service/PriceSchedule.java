package com.spring.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PriceSchedule {

    Logger log = LoggerFactory.getLogger(PriceSchedule.class);

    @Scheduled(fixedRate = 2000)
    @Async
    public void computePrice() throws InterruptedException {
        Thread.sleep(9000);
        log.info("Compute Price: "+ LocalDateTime.now());
    }
}
