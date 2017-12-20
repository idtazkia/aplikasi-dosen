package id.ac.tazkia.dosen.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by yogi on 04/05/2017.
 */
@Entity
public class KategoriKegiatan implements Serializable {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private @Getter @Setter String id;

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 255)
    private @Getter @Setter String nama;

    @Size(max = 255)
    private @Getter @Setter String keterangan;


}
