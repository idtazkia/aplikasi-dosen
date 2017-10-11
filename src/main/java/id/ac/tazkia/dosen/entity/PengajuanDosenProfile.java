package id.ac.tazkia.dosen.entity;


import id.ac.tazkia.dosen.constant.JenjangConstant;
import id.ac.tazkia.dosen.constant.StatusDosen;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "t_pengajuan_profile")
@Data
public class PengajuanDosenProfile {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_dosen")
    Dosen dosen;

    @NotNull
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    StatusDosen statusDosen;

    @NotNull
    @NotEmpty
    @Column
    String pangkat;

    @DateTimeFormat(pattern="dd-mm-yyyy")
    @Temporal(TemporalType.DATE)
    @Column(name = "tmt_pangkat")
    Date tmtPangkat;

    @DateTimeFormat(pattern="dd-mm-yyyy")
    @Temporal(TemporalType.DATE)
    @Column(name = "tmt_jabatan")
    Date tmtJabatan;
    
    @NotNull
    @Column(name = "pendidikan_tertinggi_sebelumnya")
    @Enumerated(EnumType.STRING)
    JenjangConstant pendidikanTertinggiSebelumnya;

    @NotNull
    @Column(name = "pendidikan_tertinggi_sekarang")
    @Enumerated(EnumType.STRING)
    JenjangConstant pendidikanTertinggiSekarang;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_mata_kuliah")
    MataKuliah mataKuliah;

    @NotNull
    @NotEmpty
    @Column(name = "jenis_usulan")
    String jenisUsulan;

    @NotNull
    @Column(name = "angka_kredit_dibutuhkan")
    int angkaKreditDibutuhkan;

    @NotNull
    @NotEmpty
    @Column(name = "usulan_menjadi")
    String usulanMenjadi;

    @NotNull
    @Column(name = "angka_kredit_sekarang")
    int angkaKreditSekarang;

    @NotNull
    @Column(name = "angka_kredit_usulan")
    int angkaKreditUsulan;

    @NotNull
    @NotEmpty
    @Column(name = "nomor_surat")
    String nomorSurat;

    @NotNull
    @NotEmpty
    @Column(name = "kategori_dosen")
    String kategoriDosen;

    @DateTimeFormat(pattern="dd-mm-yyyy")
    @Temporal(TemporalType.DATE)
    @Column(name = "tanggal_surat")
    Date tanggalSurat;

    @Transient
    Boolean lampiranLengkap = Boolean.FALSE;
}
