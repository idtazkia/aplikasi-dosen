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

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author ivans
 * Entity ini digunakan untuk relasi kegiatan belajar mengajar, sedangkan
 * untuk kegiatan dosen menggunakan class entity BuktiKinerjaKegiatan
 * @see id.ac.tazkia.dosen.entity.BuktiKinerjaKegiatan
 */
@Entity
@Table(name = "t_bukti_kinerja")
@Data
public class BuktiKinerja implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @NotNull
    @NotEmpty
    private String nama;

    @Column(nullable = false)
    private String url;
    
    @ManyToOne
    @JoinColumn(name = "id_kegiatan_belajar_mengajar", nullable = false)
    private KegiatanBelajarMengajar kegiatanBelajarMengajar;

    
}
