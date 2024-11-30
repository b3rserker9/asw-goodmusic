package asw.goodmusic.recensioniseguite.eventlistener;

import asw.goodmusic.common.api.event.DomainEvent;
import asw.goodmusic.connessioni.api.event.ConnessioneEventChannel;
import asw.goodmusic.recensioni.api.event.RecensioniEventChannel;
import asw.goodmusic.recensioniseguite.domain.EventConsumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class EventListener {

    private final Logger logger = Logger.getLogger(this.getClass().toString());

    @Autowired
    private EventConsumer eventConsumer;

    @KafkaListener(topics = {RecensioniEventChannel.channel, ConnessioneEventChannel.createdChannel, ConnessioneEventChannel.deletedChannel})
    public void listen(ConsumerRecord<String, DomainEvent> record) throws Exception {
        logger.info("EVENT LISTENER: " + record.toString());
        DomainEvent event = record.value();
        eventConsumer.onEvent(event);
    }
}
