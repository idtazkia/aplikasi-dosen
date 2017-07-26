package id.ac.tazkia.dosen.entity;

import id.ac.tazkia.dosen.constant.JenjangConstant;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
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