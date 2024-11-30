package asw.goodmusic.recensioniseguite.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Service
@Primary
public class RecensioniSeguiteServiceRestBasedImpl implements RecensioniSeguiteService {

    @Autowired
    private ConnessioniClientPort connessioniClient;

    @Autowired
    private RecensioniClientPort recensioniClient;

    @Autowired
    private RecensioneBreveRepository recensioneBreveRepository;

    @Autowired
    private ConnessioneRepository connessioneRepository;

    /* Trova le recensioni seguite da un utente,
     * ovvero le recensioni degli album degli artisti, dei recensori e dei generi musicali seguiti da quell'utente. */
    public Collection<RecensioneBreve> getRecensioniSeguite(String utente) {
        Collection<RecensioneBreve> recensioniSeguite = new TreeSet<>();

        //Collection<Connessione> connessioni = connessioniClient.getConnessioniByUtente(utente);
        Collection<Connessione> connessioni = connessioneRepository.findByUtente(utente);
        Collection<String> artistiSeguiti =
                connessioni
                        .stream()
                        .filter(c -> c.getRuolo().equals("ARTISTA"))
                        .map(c -> c.getSeguito())
                        .collect(Collectors.toSet());
        if (!artistiSeguiti.isEmpty()) {
            //Collection<RecensioneBreve> recensioniDiArtisti = recensioniClient.getRecensioniByArtisti(artistiSeguiti);
            Collection<RecensioneBreve> recensioniDiArtisti = recensioneBreveRepository.findByArtistaIn(artistiSeguiti);
            recensioniSeguite.addAll(recensioniDiArtisti);
        }

        Collection<String> recensoriSeguiti =
                connessioni
                        .stream()
                        .filter(c -> c.getRuolo().equals("RECENSORE"))
                        .map(c -> c.getSeguito())
                        .collect(Collectors.toSet());
        if (!recensoriSeguiti.isEmpty()) {
            //Collection<RecensioneBreve> recensioniDiRecensori = recensioniClient.getRecensioniByRecensori(recensoriSeguiti);
            Collection<RecensioneBreve> recensioniDiRecensori = recensioneBreveRepository.findByRecensoreIn(artistiSeguiti);
            recensioniSeguite.addAll(recensioniDiRecensori);
        }

        /* ok, ma purtroppo chiama il metodo getRecensioniByGeneri che non è definito né implementato */
        Collection<String> generiSeguiti =
                connessioni
                        .stream()
                        .filter(c -> c.getRuolo().equals("GENERE"))
                        .map(c -> c.getSeguito())
                        .collect(Collectors.toSet());
        if (!generiSeguiti.isEmpty()) {
            //Collection<RecensioneBreve> recensioniDiGeneri = recensioniClient.getRecensioniByGeneri(generiSeguiti);
            Collection<RecensioneBreve> recensioniDiGeneri = recensioneBreveRepository.findByGenereIn(generiSeguiti);

            recensioniSeguite.addAll(recensioniDiGeneri);
        }

        return recensioniSeguite;
    }

}
