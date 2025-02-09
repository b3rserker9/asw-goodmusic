package asw.goodmusic.connessioni.domain;

import asw.goodmusic.common.api.event.DomainEvent;
import asw.goodmusic.connessioni.api.event.ConnessioneCreatedEvent;
import asw.goodmusic.connessioni.api.event.ConnessioneDeletedEvent;
import asw.goodmusic.connessioni.eventPublisher.ConnessioneCreatedEventPublisher;
import asw.goodmusic.connessioni.eventPublisher.ConnessioneDeletedEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ConnessioniServiceImpl implements ConnessioniService {

    @Autowired
    private ConnessioniRepository connessioniRepository;

    @Autowired
    private ConnessioneCreatedEventPublisher connessioneCreatedEventPublisher;

    @Autowired
    private ConnessioneDeletedEventPublisher connessioneDeletedEventPublisher;

    /* Crea una nuova connessione, dati utente, seguito e ruolo. */
    public Connessione createConnessione(String utente, String seguito, String ruolo) {
        Connessione connessione = new Connessione(utente, seguito, ruolo);
        DomainEvent connessioneEvent = new ConnessioneCreatedEvent(utente, seguito, ruolo);
        try {
            connessione = connessioniRepository.save(connessione);
            connessioneCreatedEventPublisher.publish(connessioneEvent);
            return connessione;
        } catch (Exception e) {
            /* si potrebbe verificare un'eccezione se è violato il vincolo di unicità della connessione */
            return null;
        }
    }

    /* Trova una connessione, dato l'id. */
    public Connessione getConnessione(Long id) {
        Connessione connessione = connessioniRepository.findById(id).orElse(null);
        return connessione;
    }

    /* Trova una connessione, dati utente, seguito e ruolo. */
    public Connessione getConnessione(String utente, String seguito, String ruolo) {
        Connessione connessione = connessioniRepository.findByUtenteAndSeguitoAndRuolo(utente, seguito, ruolo);
        return connessione;
    }

    /* Trova tutte le connessioni. */
    public Collection<Connessione> getConnessioni() {
        Collection<Connessione> connessioni = connessioniRepository.findAll();
        return connessioni;
    }

    /* Trova tutte le connessioni di un utente. */
    public Collection<Connessione> getConnessioniByUtente(String utente) {
        Collection<Connessione> connessioni = connessioniRepository.findByUtente(utente);
        return connessioni;
    }

    /* Trova tutte le connessioni con un certo ruolo. */
    public Collection<Connessione> getConnessioniByRuolo(String ruolo) {
        Collection<Connessione> connessioni = connessioniRepository.findByRuolo(ruolo);
        return connessioni;
    }

    /* Trova tutte le connessioni di un utente con un certo ruolo. */
    public Collection<Connessione> getConnessioniByUtenteAndRuolo(String utente, String ruolo) {
        Collection<Connessione> connessioni = connessioniRepository.findByUtenteAndRuolo(utente, ruolo);
        return connessioni;
    }

    /* Cancella una connessione, dati utente, seguito e ruolo. */
    public Connessione deleteConnessione(String utente, String seguito, String ruolo) {
        Connessione connessione = getConnessione(utente, seguito, ruolo);
        DomainEvent connessioneEvent = new ConnessioneDeletedEvent(utente, seguito, ruolo);
        if (connessione != null) {
            connessioniRepository.delete(connessione);
            connessioneDeletedEventPublisher.publish(connessioneEvent);
        }
        return connessione;
    }

}
