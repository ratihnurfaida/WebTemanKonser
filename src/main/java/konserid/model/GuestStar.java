package konserid.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class GuestStar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String namaArtis;

    @ManyToOne
    @JoinColumn(name = "konser_id")
    private Konser konser;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNamaArtis() {
		return namaArtis;
	}

	public void setNamaArtis(String namaArtis) {
		this.namaArtis = namaArtis;
	}

	public Konser getKonser() {
		return konser;
	}

	public void setKonser(Konser konser) {
		this.konser = konser;
	}
    
}