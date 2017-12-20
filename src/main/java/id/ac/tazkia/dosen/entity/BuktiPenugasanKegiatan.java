package id.ac.tazkia.dosen.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author jimmy
 * Entity ini digunakan untuk relasi kegiatan dosen, sedangkan
 * untuk kegiatan belajar mengajar menggunakan class entity BuktiPenugasan
 * @see id.ac.tazkia.dosen.entity.BuktiPenugasan
 */
@Entity
@Table(name = "t_bukti_penugasan_kegiatan")
public class BuktiPenugasanKegiatan implements Serializable {

    public BuktiPenugasanKegiatan() {
    }

    public BuktiPenugasanKegiatan(String idKegiatan) {
        this.kegiatanDosen = new KegiatanDosen();
        this.kegiatanDosen.setId(idKegiatan);
    }
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private @Getter @Setter String id;

    @NotNull
    @NotEmpty
    private @Getter @Setter String nama;

    @Column(nullable = false)
    private @Getter @Setter String url;
    
    @ManyToOne
    @JoinColumn(name = "id_kegiatan_dosen", nullable = false)
    private @Getter @Setter KegiatanDosen kegiatanDosen;

}
