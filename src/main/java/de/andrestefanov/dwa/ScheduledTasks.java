package de.andrestefanov.dwa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    @Autowired
    private Sender sender;

    @Scheduled(cron = "0 0 11 * * MON-FRI")
    public void reportCurrentTime() {
        sender.sendInspectionRequest();
    }
}