package id.ac.tazkia.dosen.entity;

import id.ac.tazkia.dosen.constant.SemesterConstant;
import id.ac.tazkia.dosen.constant.StatusRekomendasi;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author ivans
 */
@Entity
@Table(name = "t_kegiatan_dosen")
@Data
public class KegiatanDosen  implements Serializable{

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String keterangan;

    @NotNull
    @NotEmpty
    private String periode;

    @NotNull
    @Enumerated(EnumType.STRING)
    private SemesterConstant semester;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_dosen")
    private Dosen dosen;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_jenis_kegiatan")
    private JenisKegiatan jenisKegiatan;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_kategori_kegiatan")
    private KategoriKegiatan kategoriKegiatan;

    private int sks;
    
    @Column(name = "volume")
    @NotNull
    private int volume;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_satuan_hasil_kegiatan")
    private SatuanHasilKegiatan satuanKegiatan;

    @NotNull
    @Enumerated(EnumType.STRING)
    private StatusRekomendasi status;
    
    @DateTimeFormat(pattern="dd-mm-yyyy")
    @Temporal(TemporalType.DATE)
    @Column(name = "tgl_mulai")
    private Date tanggalMulai;
    
    @DateTimeFormat(pattern="dd-mm-yyyy")
    @Temporal(TemporalType.DATE)
    @Column(name = "tgl_selesai")
    private Date tanggalSelesai;

    
}
