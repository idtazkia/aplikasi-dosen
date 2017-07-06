package id.ac.tazkia.dosen.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by yogi on 04/05/2017. Updated by Razi on 08/06/2017.
 */
@Entity
@Table(name="jenis_kegiatan")
public class JenisKegiatan implements Serializable {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "id_kategori_kegiatan")
    private KategoriKegiatan kategoriKegiatan;

    @NotNull
    @NotEmpty
    @Size(min = 1, max = 255)
    private String nama;

    @Size(max = 255)
    private String bukti;

    @Size(max = 255)
    @Column(name="batas_maksimal")        
    private String batasMaksimal;

    @Size(max = 255)
    @Column(name="angka_kredit")
    private String angkaKredit;

    public KategoriKegiatan getKategoriKegiatan() {
        return kategoriKegiatan;
    }

    public void setKategoriKegiatan(KategoriKegiatan kategoriKegiatan) {
        this.kategoriKegiatan = kategoriKegiatan;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getBukti() {
        return bukti;
    }

    public void setBukti(String bukti) {
        this.bukti = bukti;
    }

    public String getBatasMaksimal() {
        return batasMaksimal;
    }

    public void setBatasMaksimal(String batasMaksimal) {
        this.batasMaksimal = batasMaksimal;
    }

    public String getAngkaKredit() {
        return angkaKredit;
    }

    public void setAngkaKredit(String angkaKredit) {
        this.angkaKredit = angkaKredit;
    }
}