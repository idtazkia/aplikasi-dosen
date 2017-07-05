/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.tazkia.dosen.entity;

import id.ac.tazkia.dosen.constant.SemesterConstant;
import id.ac.tazkia.dosen.constant.StatusRekomendasi;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;

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

    @Column(name = "jumlah_mahasiswa")
    private int jumlahMahasiswa;

    @Cascade(CascadeType.ALL)
    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "id_bukti_penugasan")
    private BuktiPenugasan buktiPenugasan;

    @Cascade(CascadeType.ALL)
    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "id_bukti_kinerja")
    private BuktiKinerja buktiKinerja;

    @NotNull
    @Enumerated(EnumType.STRING)
    private StatusRekomendasi status;

    public Dosen getDosen() {
        return dosen;
    }

    public void setDosen(Dosen dosen) {
        this.dosen = dosen;
    }

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

    public int getJumlahMahasiswa() {
        return jumlahMahasiswa;
    }

    public void setJumlahMahasiswa(int jumlahMahasiswa) {
        this.jumlahMahasiswa = jumlahMahasiswa;
    }

    public BuktiPenugasan getBuktiPenugasan() {
        return buktiPenugasan;
    }

    public void setBuktiPenugasan(BuktiPenugasan buktiPenugasan) {
        this.buktiPenugasan = buktiPenugasan;
    }

    public BuktiKinerja getBuktiKinerja() {
        return buktiKinerja;
    }

    public void setBuktiKinerja(BuktiKinerja buktiKinerja) {
        this.buktiKinerja = buktiKinerja;
    }

    public StatusRekomendasi getStatus() {
        return status;
    }

    public void setStatus(StatusRekomendasi status) {
        this.status = status;
    }

}
