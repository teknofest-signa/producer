package teknofest.signa.producer.kafka.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import teknofest.signa.producer.model.event.BlockNotificationEvent;

@Service
@RequiredArgsConstructor
public class BlockProducer {

    private final KafkaTemplate<String, BlockNotificationEvent> kafkaTemplate;

    @Value("${topic.block-signa}")
    private String topic;

    public void send(BlockNotificationEvent blockNotificationEvent) {
        kafkaTemplate.send(topic, blockNotificationEvent);
    }
}
