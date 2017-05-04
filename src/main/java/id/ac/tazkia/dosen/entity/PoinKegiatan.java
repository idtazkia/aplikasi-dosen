package id.ac.tazkia.dosen.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by yogi on 04/05/2017.
 */
@Entity
public class PoinKegiatan implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "id_jabatan")
    private Jabatan jabatan;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "id_jenis_kegiatan")
    private JenisKegiatan jenisKegiatan;

    @NotNull
    @Min(0)
    @Max(100)
    private BigDecimal nilaiMaksimum;

    @NotNull
    @Min(0)
    @Max(100)
    private BigDecimal nilai;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Jabatan getJabatan() {
        return jabatan;
    }

    public void setJabatan(Jabatan jabatan) {
        this.jabatan = jabatan;
    }

    public JenisKegiatan getJenisKegiatan() {
        return jenisKegiatan;
    }

    public void setJenisKegiatan(JenisKegiatan jenisKegiatan) {
        this.jenisKegiatan = jenisKegiatan;
    }

    public BigDecimal getNilaiMaksimum() {
        return nilaiMaksimum;
    }

    public void setNilaiMaksimum(BigDecimal nilaiMaksimum) {
        this.nilaiMaksimum = nilaiMaksimum;
    }

    public BigDecimal getNilai() {
        return nilai;
    }

    public void setNilai(BigDecimal nilai) {
        this.nilai = nilai;
    }
}
