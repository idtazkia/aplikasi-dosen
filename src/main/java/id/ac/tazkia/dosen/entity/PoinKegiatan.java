package id.ac.tazkia.dosen.entity;

import lombok.Data;
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
@Data
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
}
