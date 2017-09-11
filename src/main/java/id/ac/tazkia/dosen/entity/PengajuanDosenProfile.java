package id.ac.tazkia.dosen.entity;


import id.ac.tazkia.dosen.constant.JenjangConstant;
import id.ac.tazkia.dosen.constant.StatusDosen;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "t_pengajuan_profile")
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
    String nip;

    @NotNull
    @NotEmpty
    @Column
    String karpeg;

    @NotNull
    @NotEmpty
    @Column
    String pangkat;

    @NotNull
    @NotEmpty
    @Column
    String golongan;

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
    @JoinColumn(name = "id_program_studi")
    ProgramStudi programStudi;

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
    @Column(name = "kode_bidang_ilmu")
    String kodeBidangIlmu;

    @NotNull
    @NotEmpty
    @Column(name = "nama_bidang_ilmu")
    String namaBidangIlmu;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getTmtJabatan() {
        return tmtJabatan;
    }

    public void setTmtJabatan(Date tmtJabatan) {
        this.tmtJabatan = tmtJabatan;
    }

    public Dosen getDosen() {
        return dosen;
    }

    public void setDosen(Dosen dosen) {
        this.dosen = dosen;
    }

    public StatusDosen getStatusDosen() {
        return statusDosen;
    }

    public void setStatusDosen(StatusDosen statusDosen) {
        this.statusDosen = statusDosen;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getKarpeg() {
        return karpeg;
    }

    public void setKarpeg(String karpeg) {
        this.karpeg = karpeg;
    }

    public String getPangkat() {
        return pangkat;
    }

    public void setPangkat(String pangkat) {
        this.pangkat = pangkat;
    }

    public String getGolongan() {
        return golongan;
    }

    public void setGolongan(String golongan) {
        this.golongan = golongan;
    }

    public Date getTmtPangkat() {
        return tmtPangkat;
    }

    public void setTmtPangkat(Date tmtPangkat) {
        this.tmtPangkat = tmtPangkat;
    }

    public JenjangConstant getPendidikanTertinggiSebelumnya() {
        return pendidikanTertinggiSebelumnya;
    }

    public void setPendidikanTertinggiSebelumnya(JenjangConstant pendidikanTertinggiSebelumnya) {
        this.pendidikanTertinggiSebelumnya = pendidikanTertinggiSebelumnya;
    }

    public JenjangConstant getPendidikanTertinggiSekarang() {
        return pendidikanTertinggiSekarang;
    }

    public void setPendidikanTertinggiSekarang(JenjangConstant pendidikanTertinggiSekarang) {
        this.pendidikanTertinggiSekarang = pendidikanTertinggiSekarang;
    }

    public ProgramStudi getProgramStudi() {
        return programStudi;
    }

    public void setProgramStudi(ProgramStudi programStudi) {
        this.programStudi = programStudi;
    }

    public MataKuliah getMataKuliah() {
        return mataKuliah;
    }

    public void setMataKuliah(MataKuliah mataKuliah) {
        this.mataKuliah = mataKuliah;
    }

    public String getJenisUsulan() {
        return jenisUsulan;
    }

    public void setJenisUsulan(String jenisUsulan) {
        this.jenisUsulan = jenisUsulan;
    }

    public int getAngkaKreditDibutuhkan() {
        return angkaKreditDibutuhkan;
    }

    public void setAngkaKreditDibutuhkan(int angkaKreditDibutuhkan) {
        this.angkaKreditDibutuhkan = angkaKreditDibutuhkan;
    }

    public String getKodeBidangIlmu() {
        return kodeBidangIlmu;
    }

    public void setKodeBidangIlmu(String kodeBidangIlmu) {
        this.kodeBidangIlmu = kodeBidangIlmu;
    }

    public String getNamaBidangIlmu() {
        return namaBidangIlmu;
    }

    public void setNamaBidangIlmu(String namaBidangIlmu) {
        this.namaBidangIlmu = namaBidangIlmu;
    }

    public String getUsulanMenjadi() {
        return usulanMenjadi;
    }

    public void setUsulanMenjadi(String usulanMenjadi) {
        this.usulanMenjadi = usulanMenjadi;
    }

    public int getAngkaKreditSekarang() {
        return angkaKreditSekarang;
    }

    public void setAngkaKreditSekarang(int angkaKreditSekarang) {
        this.angkaKreditSekarang = angkaKreditSekarang;
    }

    public int getAngkaKreditUsulan() {
        return angkaKreditUsulan;
    }

    public void setAngkaKreditUsulan(int angkaKreditUsulan) {
        this.angkaKreditUsulan = angkaKreditUsulan;
    }

    public String getNomorSurat() {
        return nomorSurat;
    }

    public void setNomorSurat(String nomorSurat) {
        this.nomorSurat = nomorSurat;
    }

    public String getKategoriDosen() {
        return kategoriDosen;
    }

    public void setKategoriDosen(String kategoriDosen) {
        this.kategoriDosen = kategoriDosen;
    }

    public Date getTanggalSurat() {
        return tanggalSurat;
    }

    public void setTanggalSurat(Date tanggalSurat) {
        this.tanggalSurat = tanggalSurat;
    }

    public Boolean getLampiranLengkap() {
        return lampiranLengkap;
    }

    public void setLampiranLengkap(Boolean lampiranLengkap) {
        this.lampiranLengkap = lampiranLengkap;
    }
}
