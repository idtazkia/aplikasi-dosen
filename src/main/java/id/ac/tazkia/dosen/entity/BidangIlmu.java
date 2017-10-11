package id.ac.tazkia.dosen.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "m_bidang_ilmu")
@Data
public class BidangIlmu implements Serializable{
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    
    @NotNull
    @NotEmpty
    @Size(min = 1, max = 255)
    private String kode;

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 255)
    private String nama;

    @Size(max = 255)
    private String keterangan;

}
