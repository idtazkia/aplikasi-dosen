package id.ac.tazkia.dosen.entity;

import lombok.Data;
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
@Data
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

}
