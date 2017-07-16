/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.tazkia.dosen.entity;

import id.ac.tazkia.dosen.constant.SemesterConstant;
import id.ac.tazkia.dosen.constant.StatusRekomendasi;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author ivans
 */
@Entity
@Table(name = "t_kegiatan_belajar_mengajar")
public class KegiatanBelajarMengajar implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String keterangan;

    @NotNull
    @NotEmpty
    private String periode;

    @NotNull
    @Enumerated(EnumType.STRING)
    private SemesterConstant semester;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_mata_kuliah")
    private MataKuliah mataKuliah;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_dosen")
    private Dosen dosen;

    @Column(name = "volume")
    @NotNull
    private int volume;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_satuan_hasil_kegiatan")
    private SatuanHasilKegiatan satuanKegiatan;

    @NotNull
    @Enumerated(EnumType.STRING)
    private StatusRekomendasi status;
    
    @Column(name = "sks_kegiatan")
    @NotNull
    private int sksKegiatan;
    
    @Column(name = "sks_mata_kuliah")
    @NotNull
    private int sksMataKuliah;
    
    @DateTimeFormat(pattern="dd-mm-yyyy")
    @Temporal(TemporalType.DATE)
    @Column(name = "tgl_mulai")
    private Date tanggalMulai;
    
    @DateTimeFormat(pattern="dd-mm-yyyy")
    @Temporal(TemporalType.DATE)
    @Column(name = "tgl_selesai")
    private Date tanggalSelesai;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getPeriode() {
        return periode;
    }

    public void setPeriode(String periode) {
        this.periode = periode;
    }

    public SemesterConstant getSemester() {
        return semester;
    }

    public void setSemester(SemesterConstant semester) {
        this.semester = semester;
    }

    public MataKuliah getMataKuliah() {
        return mataKuliah;
    }

    public void setMataKuliah(MataKuliah mataKuliah) {
        this.mataKuliah = mataKuliah;
    }

    public Dosen getDosen() {
        return dosen;
    }

    public void setDosen(Dosen dosen) {
        this.dosen = dosen;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public SatuanHasilKegiatan getSatuanKegiatan() {
        return satuanKegiatan;
    }

    public void setSatuanKegiatan(SatuanHasilKegiatan satuanKegiatan) {
        this.satuanKegiatan = satuanKegiatan;
    }

    public StatusRekomendasi getStatus() {
        return status;
    }

    public void setStatus(StatusRekomendasi status) {
        this.status = status;
    }

    public int getSksKegiatan() {
        return sksKegiatan;
    }

    public void setSksKegiatan(int sksKegiatan) {
        this.sksKegiatan = sksKegiatan;
    }

    public int getSksMataKuliah() {
        return sksMataKuliah;
    }

    public void setSksMataKuliah(int sksMataKuliah) {
        this.sksMataKuliah = sksMataKuliah;
    }

    public Date getTanggalMulai() {
        return tanggalMulai;
    }

    public void setTanggalMulai(Date tanggalMulai) {
        this.tanggalMulai = tanggalMulai;
    }

    public Date getTanggalSelesai() {
        return tanggalSelesai;
    }

    public void setTanggalSelesai(Date tanggalSelesai) {
        this.tanggalSelesai = tanggalSelesai;
    }
    
}
