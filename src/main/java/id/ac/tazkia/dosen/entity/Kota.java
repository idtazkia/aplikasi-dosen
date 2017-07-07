package id.ac.tazkia.dosen.entity;

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

@Entity
@Table(name = "kota")
public class Kota {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_provinsi")
    private Provinsi provinsi;
    
    @NotNull
    @NotEmpty
    @Size(max = 10)
    @Column(unique = true)
    private String kode;

    @NotNull
    @NotEmpty
    @Size(max = 225)
    private String nama;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Provinsi getProvinsi() {
        return provinsi;
    }
    
    public void setProvinsi(Provinsi provinsi) {
        this.provinsi = provinsi;
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
}
