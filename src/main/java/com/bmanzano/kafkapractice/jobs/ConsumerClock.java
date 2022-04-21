package com.bmanzano.kafkapractice.jobs;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
@Slf4j
public class ConsumerClock {
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

    /*
    @KafkaListener(id = "baeldung", topicPartitions =
            { @TopicPartition(topic = "baeldung", partitions = { "3", "4" }),
                    @TopicPartition(topic = "baeldung", partitions = "3",
                            partitionOffsets = @PartitionOffset(partition = "4", initialOffset = "100"))
            })
    public void listen(ConsumerRecord<?, ?> record) {
        log.info("CONSUMER4: {}", record);
    }
    */
}
