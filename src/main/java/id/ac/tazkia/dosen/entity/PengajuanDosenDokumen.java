package id.ac.tazkia.dosen.entity;

import id.ac.tazkia.dosen.constant.StatusDokumenPengajuan;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "t_pengajuan_document", uniqueConstraints={
    @UniqueConstraint(columnNames = {"id_pengajuan_profile", "id_jenis_dokumen"})
})
public class PengajuanDosenDokumen {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_pengajuan_profile")
    PengajuanDosenProfile pengajuanDosen;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_jenis_dokumen")
    JenisPengajuanDokumen jenisPengajuanDokumen;

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

    public PengajuanDosenProfile getPengajuanDosen() {
        return pengajuanDosen;
    }

    public void setPengajuanDosen(PengajuanDosenProfile pengajuanDosen) {
        this.pengajuanDosen = pengajuanDosen;
    }

    public JenisPengajuanDokumen getJenisPengajuanDokumen() {
        return jenisPengajuanDokumen;
    }

    public void setJenisPengajuanDokumen(JenisPengajuanDokumen jenisPengajuanDokumen) {
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
