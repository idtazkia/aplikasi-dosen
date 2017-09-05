package id.ac.tazkia.dosen.entity;

import id.ac.tazkia.dosen.constant.JenjangConstant;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author muhsin
 */
@Entity @Table(name = "program_studi")
public class ProgramStudi {
    @Id @GeneratedValue(generator ="uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      
    
    @NotNull
    @NotEmpty
    @Size(min = 3, max = 255)
    private String nama;
    
    @NotNull
    @NotEmpty
    @Size(max = 255)
    private String keterangan;
    
    @NotNull
    @Enumerated(EnumType.STRING)
    private JenjangConstant jenjang;

    @ManyToOne
    @JoinColumn(name = "id_fakultas")
    Fakultas fakultas;

    public Fakultas getFakultas() {
        return fakultas;
    }

    public void setFakultas(Fakultas fakultas) {
        this.fakultas = fakultas;
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

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public JenjangConstant getJenjang() {
        return jenjang;
    }

    public void setJenjang(JenjangConstant jenjang) {
        this.jenjang = jenjang;
    }


}