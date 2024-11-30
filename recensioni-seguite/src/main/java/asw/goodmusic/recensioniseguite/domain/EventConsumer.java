package asw.goodmusic.recensioniseguite.domain;

import asw.goodmusic.common.api.event.DomainEvent;
import asw.goodmusic.connessioni.api.event.ConnessioneCreatedEvent;
import asw.goodmusic.connessioni.api.event.ConnessioneDeletedEvent;
import asw.goodmusic.recensioni.api.event.RecensioneCreatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class EventConsumer {

    @Autowired
    private RecensioniSeguiteServiceDB recensioniSeguiteServiceDB;

    private final Logger logger = Logger.getLogger(this.getClass().toString());

    public void onEvent(DomainEvent event) {

        switch (event) {
            case RecensioneCreatedEvent recensioneCreatedEvent -> onRecensioneCreated(recensioneCreatedEvent);
            case ConnessioneCreatedEvent connessioneCreatedEvent -> onConnessioneCreated(connessioneCreatedEvent);
            case ConnessioneDeletedEvent connessioneDeletedEvent -> onConnessioneDeleted(connessioneDeletedEvent);
            case null, default -> logger.info("UNKNOWN EVENT: " + event);
        }
    }

    private void onRecensioneCreated(RecensioneCreatedEvent event) {
        logger.info("EVENT CONSUMER: RECENSIONE CREATED: " + event);
        RecensioneBreve recensioneBreve = new RecensioneBreve(event.getRecensore(), event.getAlbum(), event.getArtista(), event.getGenere(), event.getSunto());
        recensioniSeguiteServiceDB.createRecensione(recensioneBreve);
    }

    private void onConnessioneCreated(ConnessioneCreatedEvent event) {
        logger.info("EVENT CONSUMER: CONNESSIONE CREATED: " + event);
        Connessione connessione = new Connessione(event.getUtente(), event.getSeguito(), event.getRuolo());
        recensioniSeguiteServiceDB.createConnessione(connessione);
    }

    private void onConnessioneDeleted(ConnessioneDeletedEvent event) {
        logger.info("EVENT CONSUMER: CONNESSIONE DELETED: " + event);
        Connessione connessione = new Connessione(event.getUtente(), event.getSeguito(), event.getRuolo());
        recensioniSeguiteServiceDB.deleteConnessione(connessione);
    }

}
