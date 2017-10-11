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

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "kecamatan")
@Data
public class Kecamatan{
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_kota")
    private Kota kota;

    @NotNull
    @NotEmpty
    @Size(max = 10)
    @Column(unique = true)
    private String kode;

    @NotNull
    @NotEmpty
    @Size(max = 225)
    private String nama;
}
