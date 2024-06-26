package com.spring.config;


import net.javacrumbs.shedlock.core.LockProvider;
import net.javacrumbs.shedlock.provider.jdbctemplate.JdbcTemplateLockProvider;
import net.javacrumbs.shedlock.spring.annotation.EnableSchedulerLock;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.sql.DataSource;
import java.util.concurrent.Executor;

@Configuration
@EnableScheduling
@EnableSchedulerLock(defaultLockAtMostFor = "10m")
@ConditionalOnProperty(name = "schedule.enabled", matchIfMissing = true)
@EnableAsync
public class Schedule implements AsyncConfigurer {

    @Bean
    public LockProvider lockProvider(DataSource dataSource) {
        return new JdbcTemplateLockProvider(
                JdbcTemplateLockProvider.Configuration.builder()
                        .withJdbcTemplate(new JdbcTemplate(dataSource))
                        .usingDbTime() // Works on Postgres, MySQL, MariaDb, MS SQL, Oracle, DB2, HSQL and H2
                        .build()
        );
    }

    // For Method level
    @Bean(name = "threadPoolTaskExecutor")
    public Executor asynExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(4);
        threadPoolTaskExecutor.setMaxPoolSize(4);
        threadPoolTaskExecutor.setQueueCapacity(50);
        threadPoolTaskExecutor.setThreadNamePrefix("AsynchThread::");
        threadPoolTaskExecutor.initialize();
        return threadPoolTaskExecutor;
    }

    //For Application level
    @Override
    public Executor getAsyncExecutor() {
         ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
         threadPoolTaskExecutor.setCorePoolSize(4);
         threadPoolTaskExecutor.setMaxPoolSize(4);
         threadPoolTaskExecutor.setQueueCapacity(50);
         threadPoolTaskExecutor.initialize();
         return threadPoolTaskExecutor;
    }
}
