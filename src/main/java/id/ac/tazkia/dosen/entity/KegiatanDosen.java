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
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author ivans
 */
@Entity
@Table(name = "t_kegiatan_dosen")
public class KegiatanDosen  implements Serializable{

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private @Getter @Setter String id;

    private @Getter @Setter String keterangan;

    @NotNull
    @NotEmpty
    private @Getter @Setter String periode;

    @NotNull
    @Enumerated(EnumType.STRING)
    private @Getter @Setter SemesterConstant semester;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_dosen")
    private @Getter @Setter Dosen dosen;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_jenis_kegiatan")
    private @Getter @Setter JenisKegiatan jenisKegiatan;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_kategori_kegiatan")
    private @Getter @Setter KategoriKegiatan kategoriKegiatan;

    private @Getter @Setter int sks;
    
    @Column(name = "volume")
    @NotNull
    private @Getter @Setter int volume;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_satuan_hasil_kegiatan")
    private @Getter @Setter SatuanHasilKegiatan satuanKegiatan;

    @NotNull
    @Enumerated(EnumType.STRING)
    private @Getter @Setter StatusRekomendasi status;
    
    @DateTimeFormat(pattern="dd-mm-yyyy")
    @Temporal(TemporalType.DATE)
    @Column(name = "tgl_mulai")
    private @Getter @Setter Date tanggalMulai;
    
    @DateTimeFormat(pattern="dd-mm-yyyy")
    @Temporal(TemporalType.DATE)
    @Column(name = "tgl_selesai")
    private @Getter @Setter Date tanggalSelesai;

    
}
