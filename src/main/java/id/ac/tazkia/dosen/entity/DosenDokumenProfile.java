package id.ac.tazkia.dosen.entity;

import id.ac.tazkia.dosen.constant.StatusDokumenPengajuan;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author ari
 */
@Entity
@Table(name = "t_dosen_document_profile", uniqueConstraints={
    @UniqueConstraint(columnNames = {"id_dosen_profile", "id_jenis_dokumen_profile"})
})
public class DosenDokumenProfile {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_dosen_profile")
    Dosen dosen;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_jenis_dokumen_profile")
    JenisPengajuanDokumenProfile jenisPengajuanDokumen;

    @NotNull
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    StatusDokumenPengajuan statusDokumen;

    @NotNull
    @NotEmpty
    @Column
    String filename;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Dosen getDosen() {
        return dosen;
    }

    public void setDosen(Dosen dosen) {
        this.dosen = dosen;
    }

    public JenisPengajuanDokumenProfile getJenisPengajuanDokumen() {
        return jenisPengajuanDokumen;
    }

    public void setJenisPengajuanDokumen(JenisPengajuanDokumenProfile jenisPengajuanDokumen) {
        this.jenisPengajuanDokumen = jenisPengajuanDokumen;
    }

    public StatusDokumenPengajuan getStatusDokumen() {
        return statusDokumen;
    }

    public void setStatusDokumen(StatusDokumenPengajuan statusDokumen) {
        this.statusDokumen = statusDokumen;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
    
    
}
