package id.ac.tazkia.dosen.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author ari
 */
@Entity
@Table(name = "m_jenis_pengajuan_dokumen_profile")
@Data
public class JenisPengajuanDokumenProfile implements Serializable{
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @NotNull
    @Size(min = 3, max = 255)
    private String nama;

    @Size(max = 255)
    private String keterangan;

    @NotNull
    @Column
    Boolean required = Boolean.FALSE;
}
