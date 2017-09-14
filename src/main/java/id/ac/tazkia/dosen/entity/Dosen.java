package id.ac.tazkia.dosen.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author ronny susetyo  <ronny at susetyo.com>
 * @since 14 Apr 2017
 */
@Entity
@Table(name = "dosen")
public class Dosen implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @NotNull
    @Size(min = 3, max = 255)
    private String nama;

    @NotNull
    @NotEmpty
    private String nip;

    @NotNull
    @NotEmpty
    private String nidn;

    @NotNull
    @NotEmpty
    String karpeg;

    @NotNull
    @NotEmpty
    @Column(name = "nama_pt")
    private String namaPt;

    @NotNull
    @NotEmpty
    @Column(name = "alamat_pt")
    private String alamatPt;

    @Column(name = "lulusan_s1")
    private String s1;

    @Column(name = "lulusan_s2")
    private String s2;

    @Column(name = "lulusan_s3")
    private String s3;

    @Email
    @NotNull
    @NotEmpty
    @Column(nullable = false, unique = true)
    private String email;

    @NotNull
    @NotEmpty
    @Column(name = "no_telp")
    private String noTelp;

    @NotNull
    @NotEmpty
    @Column(name = "tempat_lahir")
    private String tempatLahir;

    @NotNull
    @Past
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Temporal(TemporalType.DATE)
    @Column(name = "tanggal_lahir", nullable = false)
    private Date tglLahir;

    @NotNull
    @NotEmpty
    private String alamat;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_kecamatan")
    private Kecamatan kecamatan;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_kota")
    private Kota kota;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_provinsi")
    private Provinsi provinsi;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_jabatan")
    private Jabatan jabatan;

    @NotNull
    @NotEmpty
    String golongan;

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
    String jenis;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_program_studi")
    ProgramStudi programStudi;

    // =============== ASESOR =============
    @ManyToOne
    @JoinColumn(name = "asesor_1")
    Dosen asesor1;

    @ManyToOne
    @JoinColumn(name = "asesor_2")
    Dosen asesor2;

    @OneToOne
    @JoinColumn(name = "id_user")
    private User user;

    public ProgramStudi getProgramStudi() {
        return programStudi;
    }

    public void setProgramStudi(ProgramStudi programStudi) {
        this.programStudi = programStudi;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getNidn() {
        return nidn;
    }

    public void setNidn(String nidn) {
        this.nidn = nidn;
    }

    public String getKarpeg() {
        return karpeg;
    }

    public void setKarpeg(String karpeg) {
        this.karpeg = karpeg;
    }

    public String getNamaPt() {
        return namaPt;
    }

    public void setNamaPt(String namaPt) {
        this.namaPt = namaPt;
    }

    public String getAlamatPt() {
        return alamatPt;
    }

    public void setAlamatPt(String alamatPt) {
        this.alamatPt = alamatPt;
    }

    public String getS1() {
        return s1;
    }

    public void setS1(String s1) {
        this.s1 = s1;
    }

    public String getS2() {
        return s2;
    }

    public void setS2(String s2) {
        this.s2 = s2;
    }

    public String getS3() {
        return s3;
    }

    public void setS3(String s3) {
        this.s3 = s3;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

    public String getTempatLahir() {
        return tempatLahir;
    }

    public void setTempatLahir(String tempatLahir) {
        this.tempatLahir = tempatLahir;
    }

    public Date getTglLahir() {
        return tglLahir;
    }

    public void setTglLahir(Date tglLahir) {
        this.tglLahir = tglLahir;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public Kecamatan getKecamatan() {
        return kecamatan;
    }

    public void setKecamatan(Kecamatan kecamatan) {
        this.kecamatan = kecamatan;
    }

    public Kota getKota() {
        return kota;
    }

    public void setKota(Kota kota) {
        this.kota = kota;
    }

    public Provinsi getProvinsi() {
        return provinsi;
    }

    public void setProvinsi(Provinsi provinsi) {
        this.provinsi = provinsi;
    }

    public Jabatan getJabatan() {
        return jabatan;
    }

    public void setJabatan(Jabatan jabatan) {
        this.jabatan = jabatan;
    }

    public String getGolongan() {
        return golongan;
    }

    public void setGolongan(String golongan) {
        this.golongan = golongan;
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

    public Dosen getAsesor1() {
        return asesor1;
    }

    public void setAsesor1(Dosen asesor1) {
        this.asesor1 = asesor1;
    }

    public Dosen getAsesor2() {
        return asesor2;
    }

    public void setAsesor2(Dosen asesor2) {
        this.asesor2 = asesor2;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

}
