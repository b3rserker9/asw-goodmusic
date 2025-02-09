package asw.goodmusic.recensioni.api.event;


import asw.goodmusic.common.api.event.DomainEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecensioneCreatedEvent implements DomainEvent {

    private String recensore;

    private String album;

    private String artista;

    private String genere;

    private String sunto;

}
