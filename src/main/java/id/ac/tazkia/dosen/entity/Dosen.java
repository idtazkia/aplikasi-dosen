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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author ronny susetyo  <ronny at susetyo.com>
 * @since 14 Apr 2017
 */
@Entity
@Table(name = "data_dosen")
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
    private String nidn;
    
    @Column(nullable = false, unique = true)
    @Email
    @NotNull
    @NotEmpty
    private String email;
    
    @NotNull
    @NotEmpty
    private String nohp;
    
    @NotNull
    @NotEmpty
    private String tlahir;
    
    @Column(name = "tanggal_lahir", nullable = false)
    @Temporal(TemporalType.DATE)
    @Past
    @NotNull
    private Date tgllahir;
    
    @NotNull
    @NotEmpty
    private String alamat;
    
    @ManyToOne
    @NotNull
    @JoinColumn(name = "kecamatan")
    private Kecamatan kecamatan;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "kota")
    private Kota kota;
    
    @ManyToOne
    @NotNull
    @JoinColumn(name = "provinsi")
    private Provinsi provinsi;


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

    public String getNidn() {
        return nidn;
    }

    public void setNidn(String nidn) {
        this.nidn = nidn;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNohp() {
        return nohp;
    }

    public void setNohp(String nohp) {
        this.nohp = nohp;
    }

    public String getTlahir() {
        return tlahir;
    }

    public void setTlahir(String tlahir) {
        this.tlahir = tlahir;
    }

    public Date getTgllahir() {
        return tgllahir;
    }

    public void setTgllahir(Date tgllahir) {
        this.tgllahir = tgllahir;
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

       
}