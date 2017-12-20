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
 * @author ronny susetyo  <ronny at susetyo.com>
 * @since 14 Apr 2017
 */
@Entity
public class JenisSurat implements Serializable {
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

    @Size(max = 255)
    private @Getter @Setter String template;
}