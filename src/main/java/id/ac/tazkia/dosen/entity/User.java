package id.ac.tazkia.dosen.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Ivans
 */
@Entity 
@Table(name = "c_security_user")
public class User {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Getter @Setter
    private String id;
    
    @Column(nullable = false, unique = true)
    @Getter @Setter
    private String username;
    
    @Column(nullable = false)
    @Getter @Setter
    private Boolean active = Boolean.TRUE;
    
    @Transient
    @Getter @Setter
    private String password;

    @JsonIgnore
    @OneToOne(mappedBy = "user", optional = true)
    @Cascade(CascadeType.ALL)
    @Getter @Setter
    private UserPassword userPassword;
    
    @ManyToOne
    @JoinColumn(name = "id_role", nullable = false)
    @Getter @Setter
    private Role role;

}
