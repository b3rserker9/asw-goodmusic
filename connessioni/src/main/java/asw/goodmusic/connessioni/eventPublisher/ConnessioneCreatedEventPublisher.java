package asw.goodmusic.connessioni.eventPublisher;


import asw.goodmusic.common.api.event.DomainEvent;
import asw.goodmusic.connessioni.api.event.ConnessioneEventChannel;
import asw.goodmusic.connessioni.domain.ConnessioniEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class ConnessioneCreatedEventPublisher implements ConnessioniEventPublisher {

    private final Logger logger = Logger.getLogger(this.getClass().toString());
    @Autowired
    private KafkaTemplate<String, DomainEvent> template;

    private String channel = ConnessioneEventChannel.createdChannel;

    @Override
    public void publish(DomainEvent event) {
        logger.info("CONNESSIONI CREATED EVENT PUBLISHER: " + event.toString() + " ON CHANNEL: " + channel);
        template.send(channel, event);
    }
}
