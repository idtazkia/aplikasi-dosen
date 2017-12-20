package id.ac.tazkia.dosen.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

/**
 * @author ronny susetyo  <ronny at susetyo.com>
 * @since 14 Apr 2017
 */
@Entity
public class SuratTugas implements Serializable {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private @Getter @Setter String id;

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 255)
    private @Getter @Setter String noSurat;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "id_jenis_surat")
    private @Getter @Setter JenisSurat jenisSurat;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "id_dosen")
    private @Getter @Setter Dosen penerima;
    
    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "tanggal_mulai")
    private @Getter @Setter Date tanggalMulai;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "tanggal_selesai")
    private @Getter @Setter Date tanggalSelesai;
}
