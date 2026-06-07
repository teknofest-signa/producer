package teknofest.signa.producer.kafka.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import teknofest.signa.producer.event.BlockEvent;
import teknofest.signa.producer.event.BlockNotificationEvent;
import teknofest.signa.producer.kafka.producer.BlockProducer;

@Slf4j
@Component
@RequiredArgsConstructor
public class BlockListener {

    private final BlockProducer blockProducer;

    @KafkaListener(topics = "${topic.block-bank}", groupId = "${spring.kafka.consumer.group-id}")
    public void onBlockEvent(BlockEvent blockEvent) {
        log.info("Received block event: {}", blockEvent.hash());
        blockProducer.send(new BlockNotificationEvent(blockEvent.hash()));
    }
}
