package id.ac.tazkia.dosen.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * @author ronny susetyo  <ronny at susetyo.com>
 * @since 14 Apr 2017
 */
@Entity
public class SuratTugas implements Serializable {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 255)
    private String noSurat;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "id_jenis_surat")
    private JenisSurat jenisSurat;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "id_dosen")
    private Dosen penerima;
    
    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "tanggal_mulai")
    private Date tanggalMulai;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "tanggal_selesai")
    private Date tanggalSelesai;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNoSurat() {
        return noSurat;
    }

    public void setNoSurat(String noSurat) {
        this.noSurat = noSurat;
    }

    public JenisSurat getJenisSurat() {
        return jenisSurat;
    }

    public void setJenisSurat(JenisSurat jenisSurat) {
        this.jenisSurat = jenisSurat;
    }

    public Dosen getPenerima() {
        return penerima;
    }

    public void setPenerima(Dosen penerima) {
        this.penerima = penerima;
    }

    public Date getTanggalMulai() {
        return tanggalMulai;
    }

    public void setTanggalMulai(Date tanggalMulai) {
        this.tanggalMulai = tanggalMulai;
    }

    public Date getTanggalSelesai() {
        return tanggalSelesai;
    }

    public void setTanggalSelesai(Date tanggalSelesai) {
        this.tanggalSelesai = tanggalSelesai;
    }
}
