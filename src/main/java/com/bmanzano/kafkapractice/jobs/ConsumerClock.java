package com.bmanzano.kafkapractice.jobs;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.listener.CommonErrorHandler;
import org.springframework.kafka.listener.ErrorHandler;
import org.springframework.kafka.listener.SeekToCurrentErrorHandler;
import org.springframework.kafka.retrytopic.TopicSuffixingStrategy;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
@Slf4j
public class ConsumerClock {
    /*
    @KafkaListener( topics = "baeldung", groupId = "1")
    public void listenGroupFoo(List<String> list,
                               @Header(KafkaHeaders.RECEIVED_PARTITION_ID) List<Integer> partitions,
                               @Header(KafkaHeaders.RECEIVED_TOPIC) List<String> topics,
                               @Header(KafkaHeaders.OFFSET) List<Long> offsets) {
        log.info("CONSUMER1: " + list);
    }

    @KafkaListener(topics = "baeldung", groupId = "2", clientIdPrefix = "2")
    public void listenGroupFoo(String message) {
        log.info("CONSUMER2: " + message);
    }

    @KafkaListener(topics = "baeldung", groupId = "3", clientIdPrefix = "3" )
    public void listenToPartition(
            @Payload String message,
            @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
        log.info("CONSUMER3: {}, partition: {}", message, partition);
    }
    */

    @KafkaListener(topics = "baeldung", groupId = "1")
    public void listenBaeldung(String message) {
        log.info("CONSUMER2:baeldung: " + message);
    }

    @RetryableTopic(
            attempts = "4",
            backoff = @Backoff(delay = 10000, multiplier = 2.0),
            autoCreateTopics = "false",
            topicSuffixingStrategy = TopicSuffixingStrategy.SUFFIX_WITH_INDEX_VALUE)
    @KafkaListener(topics = "messagesTopic", groupId = "1" )
    public void listenMessageTopic(
            @Payload String message,
            @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) throws Exception {
        log.info("CONSUMER:messagesTopic: {}, partition: {}", message, partition);
    }

}
