package id.ac.tazkia.dosen.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Ivans
 */
@Entity @Table(name = "c_security_role")
@Data
public class Role {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;
    
    @NotNull
    @NotEmpty
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "description")
    private String description;
    
    @ManyToMany
    @OrderBy("value asc")
    @JoinTable(
        name="c_security_role_permission", 
        joinColumns=@JoinColumn(name="id_role", nullable=false),
        inverseJoinColumns=@JoinColumn(name="id_permission", nullable=false)
    )
    private Set<Permission> permissionSet = new HashSet<Permission>();

}
