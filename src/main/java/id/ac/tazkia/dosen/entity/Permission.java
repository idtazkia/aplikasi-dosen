package id.ac.tazkia.dosen.entity;

/**
 *
 * @author Ivans
 */
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "c_security_permission")
@Data
public class Permission {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;

    @NotNull
    @NotEmpty
    @Column(name = "permission_label", nullable = false, unique = false)
    private String label;

    @NotNull
    @NotEmpty
    @Column(name = "permission_value", nullable = false, unique = true)
    private String value;

    @Override
    public String toString() {
        return value;
    }

}

