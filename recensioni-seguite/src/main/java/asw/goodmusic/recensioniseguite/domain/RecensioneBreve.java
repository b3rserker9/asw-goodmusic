package asw.goodmusic.recensioniseguite.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

/* Recensione (in formato breve) di un album scritta da un recensore. */
@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class RecensioneBreve implements Comparable<RecensioneBreve> {

	/* id della recensione */
	@Id@GeneratedValue
	@EqualsAndHashCode.Include
	private Long id; 
	/* chi ha scritto la recensione */ 
	private String recensore; 
	/* album oggetto della recensione */ 
	private String album; 
	/* artista autore dell'album */ 
	private String artista; 
	/* genere dell'album */ 
	private String genere; 
	/* sunto del testo della recensione */ 
	private String sunto;

	public RecensioneBreve(String recensore, String album, String artista, String genere, String sunto) {
		this.recensore = recensore;
		this.album = album;
		this.artista = artista;
		this.genere = genere;
		this.sunto = sunto;
	}

	@Override
	public int compareTo(RecensioneBreve other) {
		return this.id.compareTo(other.id); 
	}
	
}
