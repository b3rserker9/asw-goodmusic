package asw.goodmusic.recensioniseguite.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface RecensioneBreveRepository extends CrudRepository<RecensioneBreve, Long> {

    public Collection<RecensioneBreve> findByRecensoreIn(Collection<String> recensori);

    public Collection<RecensioneBreve> findByArtistaIn(Collection<String> artisti);

    public Collection<RecensioneBreve> findByGenereIn(Collection<String> generi);



}
