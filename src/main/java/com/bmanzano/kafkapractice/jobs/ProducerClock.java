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

    @Scheduled(cron = "*/10 * * * * *")
    public void start() {
        String date = LocalDateTime.now().toString();
        log.info("PRODUCER: {}", date);
        kafkaTemplate.send("baeldung", date);
    }
}
