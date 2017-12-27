package de.andrestefanov.dwa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.StringUtils;

import java.util.concurrent.Executor;

@SpringBootApplication
@EnableScheduling
@EnableAsync
public class Application {

    public static final String SLACK_CHANNEL_ID = System.getenv("SLACK_CHANNEL_ID");
    public static final String SLACK_BOT_TOKEN = System.getenv("SLACK_BOT_TOKEN");
    public static final String SLACK_BOT_NAME = System.getenv("SLACK_BOT_NAME");
    public static final String SLACK_QUESTION_TEXT = System.getenv("SLACK_QUESTION_TEXT");

    public static final String HOST_URL = System.getenv("HOST_URL");

    @Bean
    public Executor asyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(2);
        executor.setQueueCapacity(500);
        executor.setThreadNamePrefix("dwa-");
        executor.initialize();
        return executor;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
