/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.tazkia.dosen.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author razi
 */
@Entity
@Table(name = "mata_kuliah")
public class MataKuliah implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @NotNull
    @NotEmpty
    @Size(min = 1, max = 255)
    private String kode;

    @Size(min = 3, max = 255)
    private String nama;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "program_studi")
    private ProgramStudi programStudi;

    @Size(max = 255)
    private String konsentrasi;

    private Integer sks;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public ProgramStudi getProgramStudi() {
        return programStudi;
    }

    public void setProgramStudi(ProgramStudi programStudi) {
        this.programStudi = programStudi;
    }

    public String getKonsentrasi() {
        return konsentrasi;
    }

    public void setKonsentrasi(String konsentrasi) {
        this.konsentrasi = konsentrasi;
    }

    public Integer getSks() {
        return sks;
    }

    public void setSks(Integer sks) {
        this.sks = sks;
    }
}
