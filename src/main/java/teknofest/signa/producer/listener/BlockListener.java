package teknofest.signa.producer.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import teknofest.signa.producer.event.BlockEvent;

@Slf4j
@Component
@RequiredArgsConstructor
public class BlockListener {

    @KafkaListener(topics = "${topic.block}", groupId = "customer-block-topic")
    public void onBlockEvent(BlockEvent blockEvent) {

    }
}
