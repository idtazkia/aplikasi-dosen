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
@Data
public class BuktiPenugasanKegiatan implements Serializable {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @NotNull
    @NotEmpty
    private String nama;

    @Column(nullable = false)
    private String url;
    
    @ManyToOne
    @JoinColumn(name = "id_kegiatan_dosen", nullable = false)
    private KegiatanDosen kegiatanDosen;

}
