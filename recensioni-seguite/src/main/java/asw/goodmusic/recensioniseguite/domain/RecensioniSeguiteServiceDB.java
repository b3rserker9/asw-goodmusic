package asw.goodmusic.recensioniseguite.domain;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class RecensioniSeguiteServiceDB {

    @Autowired
    private ConnessioneRepository connessioneRepository;

    @Autowired
    private RecensioneBreveRepository recensioneBreveRepository;

    private final Logger logger = Logger.getLogger(this.getClass().toString());


    public void createConnessione(Connessione connessione) {
        logger.info("[RecensioniSeguiteServiceDB] SAVING CONNESSIONE: " + connessione.toString());
        try {
            connessioneRepository.save(connessione);
        } catch (Exception e) {
            logger.warning("[RecensioniSeguiteServiceDB] SAVING CONNESSIONE FAILED: " + e.getMessage());
        }
    }


    public void deleteConnessione(Connessione connessione) {
        logger.info("[RecensioniSeguiteServiceDB] DELETING CONNESSIONE: " + connessione.toString());
        try {
            connessioneRepository.delete(connessione);
        } catch (Exception e) {
            logger.warning("[RecensioniSeguiteServiceDB] DELETING CONNESSIONE FAILED: " + e.getMessage());
        }
    }


    public void createRecensione(RecensioneBreve recensioneBreve) {
        logger.info("[RecensioniSeguiteServiceDB] SAVING RECENSIONE: " + recensioneBreve.toString());
        try {
            recensioneBreveRepository.save(recensioneBreve);
            logger.info("[RecensioniSeguiteServiceDB] RECENSIONE SAVED: " + recensioneBreve.toString());
        } catch (Exception e) {
            logger.warning("[RecensioniSeguiteServiceDB] SAVING RECENSIONE FAILED: " + e.getMessage());
        }
    }
}
