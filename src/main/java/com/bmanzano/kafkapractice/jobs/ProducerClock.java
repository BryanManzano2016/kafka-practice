package com.bmanzano.kafkapractice.jobs;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service @NoArgsConstructor @Slf4j
public class ProducerClock {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Scheduled(cron = "*/30 * * * * *")
    public void start() {
        String date = LocalDateTime.now().toString();
        log.info("PRODUCER:baeldung: {}", date);
        kafkaTemplate.send("baeldung", date);
    }

    @Scheduled(cron = "*/30 * * * * *")
    public void startMessageTopic() {
        String date = LocalDateTime.now().toString();
        log.info("PRODUCER:messagesTopic : {}", date);
        kafkaTemplate.send("messagesTopic", date);
    }
}
