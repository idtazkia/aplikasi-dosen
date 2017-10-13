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
import lombok.Getter;
import lombok.Setter;

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
    @Getter @Setter
    private String id;

    @NotNull
    @Getter @Setter
    @Size(min = 3, max = 255)
    private String nama;

    @NotNull
    @NotEmpty
    @Getter @Setter
    private String nip;

    @NotNull
    @NotEmpty
    @Getter @Setter
    private String nidn;

    @NotNull
    @NotEmpty
    @Getter @Setter
    String karpeg;

    @NotNull
    @NotEmpty
    @Getter @Setter
    @Column(name = "nama_pt")
    private String namaPt;

    @NotNull
    @NotEmpty
    @Getter @Setter
    @Column(name = "alamat_pt")
    private String alamatPt;

    @Getter @Setter
    @Column(name = "lulusan_s1")
    private String s1;

    @Getter @Setter
    @Column(name = "lulusan_s2")
    private String s2;

    @Getter @Setter
    @Column(name = "lulusan_s3")
    private String s3;

    @Email
    @NotNull
    @NotEmpty
    @Getter @Setter
    @Column(nullable = false, unique = true)
    private String email;

    @NotNull
    @NotEmpty
    @Getter @Setter
    @Column(name = "no_telp")
    private String noTelp;

    @NotNull
    @NotEmpty
    @Getter @Setter
    @Column(name = "tempat_lahir")
    private String tempatLahir;

    @NotNull
    @Past
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Temporal(TemporalType.DATE)
    @Getter @Setter
    @Column(name = "tanggal_lahir", nullable = false)
    private Date tglLahir;

    @NotNull
    @NotEmpty
    @Getter @Setter
    private String alamat;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_kecamatan")
    @Getter @Setter
    private Kecamatan kecamatan;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_kota")
    @Getter @Setter
    private Kota kota;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_provinsi")
    @Getter @Setter
    private Provinsi provinsi;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_jabatan")
    @Getter @Setter
    private Jabatan jabatan;

    @NotNull
    @NotEmpty
    @Getter @Setter
    String golongan;

    @NotNull
    @NotEmpty
    @Getter @Setter
    @Column(name = "kode_bidang_ilmu")
    String kodeBidangIlmu;

    @NotNull
    @NotEmpty
    @Getter @Setter
    @Column(name = "nama_bidang_ilmu")
    String namaBidangIlmu;

    @NotNull
    @NotEmpty
    @Getter @Setter
    String jenis;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_program_studi")
    @Getter @Setter
    ProgramStudi programStudi;

    // =============== ASESOR =============
    @ManyToOne
    @JoinColumn(name = "asesor_1")
    @Getter @Setter
    Dosen asesor1;

    @ManyToOne
    @JoinColumn(name = "asesor_2")
    @Getter @Setter
    Dosen asesor2;

    @OneToOne
    @JoinColumn(name = "id_user")
    @Getter @Setter
    private User user;

}
