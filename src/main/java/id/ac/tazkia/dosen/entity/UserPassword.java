package id.ac.tazkia.dosen.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "c_security_user_password") @Data
public class UserPassword implements Serializable {

    private static final long serialVersionUID = -7371610187321351709L;

    @Id
    @Column(name = "id_user")
    private String id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id_user", nullable = false, columnDefinition = "varchar(36)")
    private User user;

    @NotNull
    @NotEmpty
    @Column
    private String password;

    public UserPassword() {
    }

    public UserPassword(User user, String password) {
        this.user = user;
        this.password = password;
    }

}
