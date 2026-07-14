package konserid.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "konser") // 
public class Konser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; // 

    @Column(name = "nama_event", nullable = false, length = 100)
    private String namaEvent; // 

    @Column(name = "deskripsi", columnDefinition = "TEXT")
    private String deskripsi; 
    
    @Column(name = "lokasi")
    private String lokasi; // 
    
    @Column(name = "tanggal_acara")
    private LocalDate tanggalAcara; 

    @Column(name = "harga_tiket", precision = 10, scale = 2)
    private BigDecimal hargaTiket; // 

    @Column(name = "status", length = 255)
    private String status; //  
    
    @Column(name = "jumlah_klik")
    private Integer jumlahKlik = 0;
    
    @OneToMany(mappedBy = "konser", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GuestStar> daftarGuestStar;

    @OneToMany(mappedBy = "konser", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Wishlist> wishlist;
    
    @Column(name = "link_tiket")
    private String linkTiket;
    
    @Column(name = "gambar")
    private String gambarKonser;
    
    public Konser() {}

    // --- Getter dan Setter ---
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNamaEvent() { return namaEvent; }
    public void setNamaEvent(String namaEvent) { this.namaEvent = namaEvent; }

    public String getDeskripsi() { return deskripsi; }
    public void setDeskripsi(String deskripsi) { this.deskripsi = deskripsi; }
    
    public String getLokasi() { return lokasi; }
    public void setLokasi(String lokasi) { this.lokasi = lokasi; }

    public LocalDate getTanggalAcara() { return tanggalAcara; }
    public void setTanggalAcara(LocalDate tanggalAcara) { this.tanggalAcara = tanggalAcara; }

    public BigDecimal getHargaTiket() { return hargaTiket; }
    public void setHargaTiket(BigDecimal hargaTiket) { this.hargaTiket = hargaTiket; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

	public List<GuestStar> getDaftarGuestStar() {
		return daftarGuestStar;
	}

	public void setDaftarGuestStar(List<GuestStar> daftarGuestStar) {
		this.daftarGuestStar = daftarGuestStar;
	}

	public Integer getJumlahKlik() {
		return jumlahKlik;
	}

	public void setJumlahKlik(Integer jumlahKlik) {
		this.jumlahKlik = jumlahKlik;
	}

	public String getLinkTiket() {
		return linkTiket;
	}

	public void setLinkTiket(String linkTiket) {
		this.linkTiket = linkTiket;
	}

	public List<Wishlist> getWishlist() {
		return wishlist;
	}

	public void setWishlist(List<Wishlist> wishlist) {
		this.wishlist = wishlist;
	}

	public String getGambar() {
		return gambarKonser;
	}

	public void setGambar(String gambarKonser) {
		this.gambarKonser = gambarKonser;
	}
}